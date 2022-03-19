package rs.laxsrbija.chores.messaging.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rs.laxsrbija.chores.core.service.TaskService;
import rs.laxsrbija.chores.shared.model.dto.Task;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReminderService
{
	private final TaskService _taskService;
	private final EmailService _emailService;

//	@Scheduled(cron = "0 0 7 * * ?")
	public void checkAllTasksForReminders()
	{
		_taskService.getAllTasks().stream()
			.filter(ReminderService::shouldRemind)
			.forEach(_emailService::sendReminder);
	}

	private static boolean shouldRemind(final Task task)
	{
		if (!task.isEnabled() || task.getHistory().isEmpty())
		{
			return false;
		}

		final long daysUntilNextRecurrence = TaskService.getDaysUntilNextRecurrence(task);
		final long daysToRemindBeforeRecurrence = task.getReminder().getReminderDate().getNumberOfDays();

		return daysToRemindBeforeRecurrence == daysUntilNextRecurrence;
	}
}
