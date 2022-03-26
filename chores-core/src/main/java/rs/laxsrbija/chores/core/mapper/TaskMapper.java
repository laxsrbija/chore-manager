package rs.laxsrbija.chores.core.mapper;

import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.core.service.TaskService;
import rs.laxsrbija.chores.data.entity.TaskEntity;
import rs.laxsrbija.chores.shared.model.dto.Object;
import rs.laxsrbija.chores.shared.model.dto.Task;

@Component
public class TaskMapper
{
	public Task toTask(final TaskEntity taskEntity, final Object object)
	{
		final Task task = Task.builder()
			.id(taskEntity.getId())
			.name(taskEntity.getName())
			.enabled(taskEntity.isEnabled())
			.history(taskEntity.getHistory())
			.recurrence(taskEntity.getRecurrence())
			.reminder(taskEntity.getReminder())
			.object(object)
			.build();

		task.setDaysUntilNextRecurrence(TaskService.getDaysUntilNextRecurrence(task));
		task.setNextRecurrence(TaskService.getNextRecurrence(task));
		return task;
	}
}
