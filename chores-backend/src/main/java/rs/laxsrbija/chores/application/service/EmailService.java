package rs.laxsrbija.chores.application.service;

import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import rs.laxsrbija.chores.domain.Task;
import rs.laxsrbija.chores.domain.User;

@Slf4j
@Service
@RequiredArgsConstructor
class EmailService {

  private final UserService userService;
  private final JavaMailSender mailSender;

  void sendReminder(final Task task) {
    final List<String> usersToNotify = task.getReminder().getUsersToNotify();
    for (final String userId : usersToNotify) {
      final User user = userService.get(userId);
      remindUser(user, task);
    }
  }

  private void remindUser(final User user, final Task task) {
    final MimeMessage message = mailSender.createMimeMessage();
    final MimeMessageHelper messageHelper = new MimeMessageHelper(message);

    final String subject = "[CM] " + task.getName() + " - " + task.getNextOcurrence();
    final String text = getReminderText(user, task);

    try {
      messageHelper.setFrom("Chore Manager <cm@example.com>");
      messageHelper.setTo(user.getEmail());
      messageHelper.setSubject(subject);
      messageHelper.setText(text, true);
    } catch (final MessagingException e) {
      log.error("Unable to create an email reminder", e);
    }

    mailSender.send(message);
  }

  private String getReminderText(final User user, final Task task) {
    return "Hi " + user.getName() + ",<br><br>"
        + "This is a reminder about the following task:<br>"
        + "Task name: <strong>" + task.getName() + "</strong><br>"
        + "Due date: <strong>" + task.getNextOcurrence() + "</strong> "
        + "(in <strong>" + task.getDaysUntilNextOcurrence() + " days</strong>)<br><br>"
        + "Chore Manager";
  }
}
