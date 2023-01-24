package net.lazars.chores.adapter.db.mapper;

import static net.lazars.chores.core.util.Commons.forEach;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import net.lazars.chores.adapter.db.entity.TaskEntity;
import net.lazars.chores.adapter.db.entity.embedded.CompletionHistoryItemEntity;
import net.lazars.chores.adapter.db.entity.embedded.ReminderInfoEntity;
import net.lazars.chores.core.helper.OccurrenceHelper;
import net.lazars.chores.core.model.CompletionHistoryItem;
import net.lazars.chores.core.model.Item;
import net.lazars.chores.core.model.OccurrenceInfo;
import net.lazars.chores.core.model.ReminderInfo;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

  public Task toTask(final TaskEntity taskEntity, final Item item, final List<User> users) {
    final Task task =
        Task.builder()
            .id(taskEntity.getId())
            .name(taskEntity.getName())
            .dateCreated(taskEntity.getDateCreated())
            .description(taskEntity.getDescription())
            .enabled(taskEntity.isEnabled())
            .history(forEach(taskEntity.getHistory(), users, this::toCompletionHistoryItem))
            .recurrence(taskEntity.getRecurrence())
            .reminder(toReminderInfo(taskEntity.getReminder(), users))
            .item(item)
            .build();

    final OccurrenceInfo occurrenceInfo =
        OccurrenceInfo.builder()
            .daysUntilNextOccurrence(OccurrenceHelper.getDaysUntilNextOccurrence(task))
            .nextOccurrence(OccurrenceHelper.getNextOccurrence(task))
            .build();
    task.setOccurrence(occurrenceInfo);

    return task;
  }

  public TaskEntity toTaskEntity(final Task task) {
    return TaskEntity.builder()
        .id(task.getId())
        .name(task.getName())
        .dateCreated(task.getDateCreated() == null ? LocalDate.now() : task.getDateCreated())
        .description(task.getDescription())
        .enabled(task.isEnabled())
        .history(forEach(task.getHistory(), this::toCompletionHistoryItemEntity))
        .recurrence(task.getRecurrence())
        .reminder(toReminderInfoEntity(task.getReminder()))
        .itemId(task.getItem().getId())
        .build();
  }

  private CompletionHistoryItem toCompletionHistoryItem(
      final CompletionHistoryItemEntity completionHistoryItemEntity, final List<User> users) {
    final User completionUser =
        users.stream()
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

  private ReminderInfo toReminderInfo(final ReminderInfoEntity entity, final List<User> users) {
    final Set<String> usersIdsToNotify = entity.getUsersIdsToNotify();
    final List<User> usersToNotify =
        users.stream()
            .filter(user -> usersIdsToNotify != null && usersIdsToNotify.contains(user.getId()))
            .toList();

    return ReminderInfo.builder()
        .usersToNotify(usersToNotify)
        .reminderDate(entity.getReminderDate())
        .build();
  }

  private ReminderInfoEntity toReminderInfoEntity(final ReminderInfo reminderInfo) {
    final Set<String> userIdsToNotify =
        reminderInfo.getUsersToNotify().stream().map(User::getId).collect(Collectors.toSet());

    return ReminderInfoEntity.builder()
        .reminderDate(reminderInfo.getReminderDate())
        .usersIdsToNotify(userIdsToNotify)
        .build();
  }
}
