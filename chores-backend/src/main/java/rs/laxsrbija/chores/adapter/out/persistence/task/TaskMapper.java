package rs.laxsrbija.chores.adapter.out.persistence.task;

import static rs.laxsrbija.chores.common.Commons.forEach;

import java.util.List;
import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.adapter.out.persistence.entity.TaskEntity;
import rs.laxsrbija.chores.adapter.out.persistence.entity.embedded.CompletionHistoryItemEntity;
import rs.laxsrbija.chores.application.util.OccurrenceUtil;
import rs.laxsrbija.chores.domain.CompletionHistoryItem;
import rs.laxsrbija.chores.domain.Item;
import rs.laxsrbija.chores.domain.Task;
import rs.laxsrbija.chores.domain.User;

@Component
class TaskMapper {

  public Task toTask(final TaskEntity taskEntity, final Item item, final List<User> users) {
    final Task task = Task.builder()
        .id(taskEntity.getId())
        .name(taskEntity.getName())
        .description(taskEntity.getDescription())
        .enabled(taskEntity.isEnabled())
        .history(forEach(taskEntity.getHistory(), users, this::toCompletionHistoryItem))
        .recurrence(taskEntity.getRecurrence())
        .reminder(taskEntity.getReminder())
        .item(item)
        .build();

    task.setDaysUntilNextOcurrence(OccurrenceUtil.getDaysUntilNextOccurrence(task));
    task.setNextOcurrence(OccurrenceUtil.getNextOccurrence(task));
    return task;
  }

  public TaskEntity toTaskEntity(final Task task) {
    return TaskEntity.builder()
        .id(task.getId())
        .name(task.getName())
        .description(task.getDescription())
        .enabled(task.isEnabled())
        .history(forEach(task.getHistory(), this::toCompletionHistoryItemEntity))
        .recurrence(task.getRecurrence())
        .reminder(task.getReminder())
        .itemId(task.getItem().getId())
        .build();
  }

  private CompletionHistoryItem toCompletionHistoryItem(
      final CompletionHistoryItemEntity completionHistoryItemEntity,
      final List<User> users) {
    final User completionUser = users.stream()
        .filter(user -> user.getId().equals(completionHistoryItemEntity.getUserId()))
        .findFirst()
        .orElse(null);

    return CompletionHistoryItem.builder()
        .dateCompleted(completionHistoryItemEntity.getDateCompleted())
        .user(completionUser)
        .build();
  }

  private CompletionHistoryItemEntity toCompletionHistoryItemEntity(
      final CompletionHistoryItem completionHistoryItem) {
    return CompletionHistoryItemEntity.builder()
        .dateCompleted(completionHistoryItem.getDateCompleted())
        .userId(completionHistoryItem.getUser().getId())
        .build();
  }
}
