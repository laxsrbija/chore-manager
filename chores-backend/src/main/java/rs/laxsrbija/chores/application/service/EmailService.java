package rs.laxsrbija.chores.application.service;

import java.util.List;
import java.util.stream.Collectors;
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

  private static final String EMAIL_SUBJECT_PREFIX = "[CM] ";

  private final JavaMailSender mailSender;
  private final EmailTemplateService emailTemplateService;

  public void sendTaskReminder(final Task task) {
    final List<User> usersToNotify = task.getReminder().getUsersToNotify();
    final String subject = task.getName() + " - " + task.getOccurrence().getNextOccurrence();

    for (final User user : usersToNotify) {
      final String text = emailTemplateService.parseTaskReminderEmailTemplate(task, user);
      sendEmail(subject, text, user.getEmail());
    }
  }

  public void sendTaskCompleteByDifferentUserNotification(final Task task, final User user) {
    final List<User> otherUsers =
        task.getReminder().getUsersToNotify().stream()
            .filter(userToNotify -> !userToNotify.getId().equals(user.getId()))
            .collect(Collectors.toList());
    final String subject = task.getName() + " completed by " + user.getName();

    for (final User otherUser : otherUsers) {
      final String text =
          emailTemplateService.parseTaskCompleteByDifferentUserEmailTemplate(task, user, otherUser);
      sendEmail(subject, text, otherUser.getEmail());
    }
  }

  private void sendEmail(final String subject, final String content, final String recipient) {
    final MimeMessage message = mailSender.createMimeMessage();
    final MimeMessageHelper messageHelper = new MimeMessageHelper(message);

    try {
      messageHelper.setFrom("Chore Manager <cm@example.com>");
      messageHelper.setTo(recipient);
      messageHelper.setSubject(EMAIL_SUBJECT_PREFIX + subject);
      messageHelper.setText(content, true);
    } catch (final MessagingException e) {
      log.error("Unable to create an email reminder", e);
    }

    mailSender.send(message);
  }
}
