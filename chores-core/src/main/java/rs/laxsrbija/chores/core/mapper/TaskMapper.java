package rs.laxsrbija.chores.core.mapper;

import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.data.entity.TaskEntity;
import rs.laxsrbija.chores.shared.model.dto.Task;

@Component
public class TaskMapper
{
	public Task toTask(final TaskEntity task)
	{
		return Task.builder()
			.id(task.getId())
			.name(task.getName())
			.image(task.getImage())
			.enabled(task.isEnabled())
			.history(task.getHistory())
			.recurrence(task.getRecurrence())
			.reminder(task.getReminder())
			.build();
	}

	public TaskEntity toTaskEntity(final Task task)
	{
		return TaskEntity.builder()
			.id(task.getId())
			.name(task.getName())
			.image(task.getImage())
			.enabled(task.isEnabled())
			.history(task.getHistory())
			.recurrence(task.getRecurrence())
			.reminder(task.getReminder())
			.build();
	}
}
