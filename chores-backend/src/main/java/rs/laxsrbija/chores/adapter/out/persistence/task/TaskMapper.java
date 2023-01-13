package rs.laxsrbija.chores.adapter.out.persistence.task;

import static rs.laxsrbija.chores.common.Commons.forEach;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.adapter.out.persistence.entity.TaskEntity;
import rs.laxsrbija.chores.adapter.out.persistence.entity.embedded.CompletionHistoryItemEntity;
import rs.laxsrbija.chores.adapter.out.persistence.entity.embedded.ReminderInfoEntity;
import rs.laxsrbija.chores.application.util.OccurrenceUtil;
import rs.laxsrbija.chores.domain.CompletionHistoryItem;
import rs.laxsrbija.chores.domain.Item;
import rs.laxsrbija.chores.domain.OccurrenceInfo;
import rs.laxsrbija.chores.domain.ReminderInfo;
import rs.laxsrbija.chores.domain.Task;
import rs.laxsrbija.chores.domain.User;

@Component
class TaskMapper {

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
            .daysUntilNextOccurrence(OccurrenceUtil.getDaysUntilNextOccurrence(task))
            .nextOccurrence(OccurrenceUtil.getNextOccurrence(task))
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
