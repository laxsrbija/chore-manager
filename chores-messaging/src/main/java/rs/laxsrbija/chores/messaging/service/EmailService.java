package rs.laxsrbija.chores.messaging.service;

import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rs.laxsrbija.chores.core.service.TaskService;
import rs.laxsrbija.chores.core.service.UserService;
import rs.laxsrbija.chores.shared.model.dto.Task;
import rs.laxsrbija.chores.shared.model.dto.User;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService
{
	private final UserService _userService;
	private final JavaMailSender _mailSender;

	public void sendReminder(final Task task)
	{
		final List<String> usersToNotify = task.getReminder().getUsersToNotify();
		for (final String userId : usersToNotify)
		{
			final User user = _userService.getUser(userId);
			remindUser(user, task);
		}
	}

	private void remindUser(final User user, final Task task)
	{
		final MimeMessage message = _mailSender.createMimeMessage();
		final MimeMessageHelper messageHelper = new MimeMessageHelper(message);

		final String subject = "[CM] " + task.getName() + " - " + TaskService.getNextRecurrence(task);
		final String text = getReminderText(user, task);

		try
		{
			messageHelper.setFrom("Chore Manager <cm@example.com>");
			messageHelper.setTo(user.getEmail());
			messageHelper.setSubject(subject);
			messageHelper.setText(text, true);
		}
		catch (final MessagingException e)
		{
			log.error("Unable to create an email reminder", e);
		}

		_mailSender.send(message);
	}

	private String getReminderText(final User user, final Task task)
	{
		return "Hi " + user.getName() + ",<br><br>"
			+ "This is a reminder about the following task:<br>"
			+ "Task name: <strong>" + task.getName() + "</strong><br>"
			+ "Due date: <strong>" + TaskService.getNextRecurrence(task) + "</strong> "
			+ "(in <strong>" + TaskService.getDaysUntilNextRecurrence(task) + " days</strong>)<br><br>"
			+ "Chore Manager";
	}
}
