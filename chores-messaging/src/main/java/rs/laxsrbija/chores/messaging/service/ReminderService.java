package rs.laxsrbija.chores.messaging.service;

import java.time.LocalDate;
import java.util.List;
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
public class ReminderService
{
	private final TaskService _taskService;
	private final UserService _userService;

//	@Scheduled
	public void checkAllTasks()
	{
		_taskService.getAllTasks().stream()
			.filter(task -> !task.getHistory().isEmpty() && shouldRemind(task))
			.forEach(this::notifyUsers);
	}

	private void notifyUsers(final Task task)
	{
		final String taskName = task.getName();
		final LocalDate nextRecurrence = TaskService.getNextRecurrence(task);
		final long daysUntilNextRecurrence = TaskService.getDaysUntilNextRecurrence(task);

		final List<String> usersToNotify = task.getReminder().getUsersToNotify();
		for (final String userId : usersToNotify)
		{
			final User user = _userService.getUser(userId);
			log.info("Notifying " + user.getName() + " via " + user.getEmail() + ": "
				+ taskName + " should be done on " + nextRecurrence + "(" + daysUntilNextRecurrence + ")");
		}
	}

	private static boolean shouldRemind(final Task task)
	{
		final long daysUntilNextRecurrence = TaskService.getDaysUntilNextRecurrence(task);
		final long daysToRemindBeforeRecurrence = task.getReminder().getReminderDate().getNumberOfDays();

		return daysToRemindBeforeRecurrence == daysUntilNextRecurrence;
	}
}
