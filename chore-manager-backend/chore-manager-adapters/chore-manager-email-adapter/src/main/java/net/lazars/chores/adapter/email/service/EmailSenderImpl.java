package net.lazars.chores.adapter.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lazars.chores.core.model.NotificationChannel;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.out.NotificationSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSenderImpl implements NotificationSender {

  private static final int NUMBER_OF_RETRIES = 10;

  private final JavaMailSender mailSender;
  private final EmailTemplateService emailTemplateService;

  @Override
  public NotificationChannel getChannel() {
    return NotificationChannel.EMAIL;
  }

  @Override
  public void sendTaskReminder(final User user, final Task task, final String title) {
    final String text = emailTemplateService.parseTaskReminderEmailTemplate(task, user);
    sendEmail(title, text, user.getEmail());
  }

  @Override
  public void sendTaskCompleteByDifferentUserNotification(
      final User user, final Task task, final User otherUser, final String title) {
    final String text =
        emailTemplateService.parseTaskCompleteByDifferentUserEmailTemplate(task, otherUser, user);
    sendEmail(title, text, user.getEmail());
  }

  private void sendEmail(final String subject, final String content, final String recipient) {
    final MimeMessage message = mailSender.createMimeMessage();
    final MimeMessageHelper messageHelper = new MimeMessageHelper(message);

    try {
      messageHelper.setFrom("Chore Manager <cm@example.com>");
      messageHelper.setTo(recipient);
      messageHelper.setSubject(subject);
      messageHelper.setText(content, true);
    } catch (final MessagingException e) {
      log.error("Unable to create an email reminder", e);
    }

    sendEmailWithRetries(message);
  }

  private void sendEmailWithRetries(final MimeMessage message) {
    for (int currentAttempt = 1; currentAttempt <= NUMBER_OF_RETRIES; ++currentAttempt) {
      try {
        mailSender.send(message);
        return;
      } catch (final Exception e) {
        try {
          TimeUnit.SECONDS.sleep(5);
        } catch (final InterruptedException ex) {
          Thread.currentThread().interrupt();
          throw new IllegalStateException(ex);
        }

        log.warn("Failed to send the email message: Attempt number " + currentAttempt);
      }
    }

    log.error("Failed to send the email message after " + NUMBER_OF_RETRIES + " attempts");
  }
}
