package rs.laxsrbija.chores.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.adapter.out.persistence.entity.TaskEntity;
import rs.laxsrbija.chores.application.service.TaskService;
import rs.laxsrbija.chores.domain.model.dto.Object;
import rs.laxsrbija.chores.domain.model.dto.Task;

@Component
public class TaskMapper {

  public Task toTask(final TaskEntity taskEntity, final Object object) {
    final Task task = Task.builder()
        .id(taskEntity.getId())
        .name(taskEntity.getName())
        .description(taskEntity.getDescription())
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

  public TaskEntity toTaskEntity(final Task task) {
    return TaskEntity.builder()
        .id(task.getId())
        .name(task.getName())
        .description(task.getDescription())
        .enabled(task.isEnabled())
        .history(task.getHistory())
        .recurrence(task.getRecurrence())
        .reminder(task.getReminder())
        .objectId(task.getObject().getId())
        .build();
  }
}
