package net.lazars.chores.adapter.db.mapper;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import net.lazars.chores.adapter.db.entity.TaskDocument;
import net.lazars.chores.adapter.db.entity.embedded.CompletionHistoryItemPart;
import net.lazars.chores.adapter.db.entity.embedded.ReminderInfoPart;
import net.lazars.chores.adapter.db.entity.embedded.recurrence.DynamicRecurrencePart;
import net.lazars.chores.adapter.db.entity.embedded.recurrence.FixedRecurrencePart;
import net.lazars.chores.adapter.db.entity.embedded.recurrence.RecurrencePart;
import net.lazars.chores.core.helper.OccurrenceHelper;
import net.lazars.chores.core.model.CompletionHistoryItem;
import net.lazars.chores.core.model.Item;
import net.lazars.chores.core.model.OccurrenceInfo;
import net.lazars.chores.core.model.ReminderInfo;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.model.recurrence.DynamicRecurrence;
import net.lazars.chores.core.model.recurrence.FixedRecurrence;
import net.lazars.chores.core.model.recurrence.Recurrence;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

  public Task toTask(final TaskDocument taskEntity, final Item item, final List<User> users) {
    final Task task =
        Task.builder()
            .id(taskEntity.getId())
            .name(taskEntity.getName())
            .dateCreated(taskEntity.getDateCreated())
            .description(taskEntity.getDescription())
            .enabled(taskEntity.isEnabled())
            .history(forEach(taskEntity.getHistory(), users, this::toCompletionHistoryItem))
            .recurrence(toRecurrence(taskEntity.getRecurrence()))
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

  public TaskDocument toTaskDocument(final Task task) {
    return TaskDocument.builder()
        .id(task.getId())
        .name(task.getName())
        .dateCreated(task.getDateCreated() == null ? LocalDate.now() : task.getDateCreated())
        .description(task.getDescription())
        .enabled(task.isEnabled())
        .history(forEach(task.getHistory(), this::toCompletionHistoryItemEntity))
        .recurrence(toRecurrencePart(task.getRecurrence()))
        .reminder(toReminderInfoEntity(task.getReminder()))
        .itemId(task.getItem().getId())
        .build();
  }

  private CompletionHistoryItem toCompletionHistoryItem(
      final CompletionHistoryItemPart completionHistoryItemEntity, final List<User> users) {
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

  private CompletionHistoryItemPart toCompletionHistoryItemEntity(
      final CompletionHistoryItem completionHistoryItem) {
    return CompletionHistoryItemPart.builder()
        .dateCompleted(completionHistoryItem.getDateCompleted())
        .userId(completionHistoryItem.getUser().getId())
        .build();
  }

  private ReminderInfo toReminderInfo(final ReminderInfoPart entity, final List<User> users) {
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

  private ReminderInfoPart toReminderInfoEntity(final ReminderInfo reminderInfo) {
    final Set<String> userIdsToNotify =
        reminderInfo.getUsersToNotify().stream().map(User::getId).collect(Collectors.toSet());

    return ReminderInfoPart.builder()
        .reminderDate(reminderInfo.getReminderDate())
        .usersIdsToNotify(userIdsToNotify)
        .build();
  }

  private Recurrence toRecurrence(final RecurrencePart recurrencePart) {
    if (recurrencePart instanceof FixedRecurrencePart fixedRecurrencePart) {
      return toFixedRecurrence(fixedRecurrencePart);
    } else if (recurrencePart instanceof DynamicRecurrencePart dynamicRecurrencePart) {
      return toDynamicRecurrence(dynamicRecurrencePart);
    }

    return null;
  }

  private FixedRecurrence toFixedRecurrence(final FixedRecurrencePart fixedRecurrencePart) {
    return FixedRecurrence.builder()
        .day(fixedRecurrencePart.getDay())
        .month(fixedRecurrencePart.getMonth())
        .build();
  }

  private DynamicRecurrence toDynamicRecurrence(final DynamicRecurrencePart dynamicRecurrencePart) {
    return DynamicRecurrence.builder()
        .dateUnit(dynamicRecurrencePart.getDateUnit())
        .frequency(dynamicRecurrencePart.getFrequency())
        .build();
  }

  private RecurrencePart toRecurrencePart(final Recurrence recurrence) {
    if (recurrence instanceof FixedRecurrence fixedRecurrence) {
      return toFixedRecurrencePart(fixedRecurrence);
    } else if (recurrence instanceof DynamicRecurrence dynamicRecurrence) {
      return toDynamicRecurrencePart(dynamicRecurrence);
    }

    return null;
  }

  private FixedRecurrencePart toFixedRecurrencePart(final FixedRecurrence fixedRecurrence) {
    return FixedRecurrencePart.builder()
        .day(fixedRecurrence.getDay())
        .month(fixedRecurrence.getMonth())
        .build();
  }

  private DynamicRecurrencePart toDynamicRecurrencePart(final DynamicRecurrence dynamicRecurrence) {
    return DynamicRecurrencePart.builder()
        .dateUnit(dynamicRecurrence.getDateUnit())
        .frequency(dynamicRecurrence.getFrequency())
        .build();
  }
}
