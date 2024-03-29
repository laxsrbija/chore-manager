package net.lazars.chores.adapter.redis.mapper;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import net.lazars.chores.adapter.redis.document.CompletionHistoryItemPart;
import net.lazars.chores.adapter.redis.document.DeferInfoPart;
import net.lazars.chores.adapter.redis.document.RecurrenceType;
import net.lazars.chores.adapter.redis.document.ReminderInfoPart;
import net.lazars.chores.adapter.redis.document.TaskDocument;
import net.lazars.chores.adapter.redis.document.TaskDocument.TaskDocumentBuilder;
import net.lazars.chores.core.helper.OccurrenceHelper;
import net.lazars.chores.core.model.CompletionHistoryItem;
import net.lazars.chores.core.model.DeferInfo;
import net.lazars.chores.core.model.Item;
import net.lazars.chores.core.model.OccurrenceInfo;
import net.lazars.chores.core.model.ReminderInfo;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.model.date.DatePeriod;
import net.lazars.chores.core.model.recurrence.DynamicRecurrence;
import net.lazars.chores.core.model.recurrence.FixedRecurrence;
import net.lazars.chores.core.model.recurrence.Recurrence;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

  public Task toTask(final TaskDocument taskDocument, final Item item, final List<User> users) {
    final Task task =
        Task.builder()
            .id(taskDocument.getId())
            .name(taskDocument.getName())
            .dateCreated(taskDocument.getDateCreated())
            .description(taskDocument.getDescription())
            .enabled(taskDocument.isEnabled())
            .history(forEach(taskDocument.getHistory(), users, this::toCompletionHistoryItem))
            .recurrence(toRecurrence(taskDocument))
            .reminder(toReminderInfo(taskDocument.getReminder(), users))
            .item(item)
            .defer(
                taskDocument.getDefer() != null
                    ? toDeferInfo(taskDocument.getDefer(), users)
                    : null)
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
    final TaskDocumentBuilder builder =
        TaskDocument.builder()
            .id(task.getId())
            .name(task.getName())
            .dateCreated(task.getDateCreated() == null ? LocalDate.now() : task.getDateCreated())
            .description(task.getDescription())
            .enabled(task.isEnabled())
            .history(forEach(task.getHistory(), this::toCompletionHistoryItemPart))
            .reminder(toReminderInfoPart(task.getReminder()))
            .itemId(task.getItem().getId())
            .defer(task.getDefer() != null ? toDeferInfoPart(task.getDefer()) : null);

    if (task.getRecurrence() instanceof FixedRecurrence fixedRecurrence) {
      builder
          .recurrenceType(RecurrenceType.FIXED)
          .fixedRecurrenceDay(fixedRecurrence.getDay())
          .fixedRecurrenceMonth(fixedRecurrence.getMonth());
    } else if (task.getRecurrence() instanceof DynamicRecurrence dynamicRecurrence) {
      builder
          .recurrenceType(RecurrenceType.DYNAMIC)
          .dynamicRecurrencePeriod(
              DatePeriod.builder()
                  .dateUnit(dynamicRecurrence.getDateUnit())
                  .frequency(dynamicRecurrence.getFrequency())
                  .build());
    }

    return builder.build();
  }

  private DeferInfo toDeferInfo(final DeferInfoPart deferInfoPart, final List<User> users) {
    return DeferInfo.builder()
        .deferDate(deferInfoPart.getDeferDate())
        .user(
            users.stream()
                .filter(user -> user.getId().equals(deferInfoPart.getUserId()))
                .findFirst()
                .orElse(null))
        .build();
  }

  private DeferInfoPart toDeferInfoPart(final DeferInfo deferInfo) {
    return DeferInfoPart.builder()
        .deferDate(deferInfo.getDeferDate())
        .userId(deferInfo.getUser().getId())
        .build();
  }

  private CompletionHistoryItem toCompletionHistoryItem(
      final CompletionHistoryItemPart completionHistoryItemPart, final List<User> users) {
    final User completionUser =
        users.stream()
            .filter(user -> user.getId().equals(completionHistoryItemPart.getUserId()))
            .findFirst()
            .orElse(null);

    return CompletionHistoryItem.builder()
        .dateCompleted(completionHistoryItemPart.getDateCompleted())
        .user(completionUser)
        .build();
  }

  private CompletionHistoryItemPart toCompletionHistoryItemPart(
      final CompletionHistoryItem completionHistoryItem) {
    return CompletionHistoryItemPart.builder()
        .dateCompleted(completionHistoryItem.getDateCompleted())
        .userId(completionHistoryItem.getUser().getId())
        .build();
  }

  private ReminderInfo toReminderInfo(final ReminderInfoPart document, final List<User> users) {
    final Set<String> usersIdsToNotify = document.getUsersIdsToNotify();
    final List<User> usersToNotify =
        users.stream()
            .filter(user -> usersIdsToNotify != null && usersIdsToNotify.contains(user.getId()))
            .toList();

    return ReminderInfo.builder()
        .usersToNotify(usersToNotify)
        .reminderDate(document.getReminderDate())
        .build();
  }

  private ReminderInfoPart toReminderInfoPart(final ReminderInfo reminderInfo) {
    final Set<String> userIdsToNotify =
        reminderInfo.getUsersToNotify().stream().map(User::getId).collect(Collectors.toSet());

    return ReminderInfoPart.builder()
        .reminderDate(reminderInfo.getReminderDate())
        .usersIdsToNotify(userIdsToNotify)
        .build();
  }

  private Recurrence toRecurrence(final TaskDocument taskDocument) {
    if (taskDocument.getRecurrenceType() == RecurrenceType.FIXED) {
      return toFixedRecurrence(taskDocument);
    } else if (taskDocument.getRecurrenceType() == RecurrenceType.DYNAMIC) {
      return toDynamicRecurrence(taskDocument);
    }

    return null;
  }

  private FixedRecurrence toFixedRecurrence(final TaskDocument taskDocument) {
    return FixedRecurrence.builder()
        .day(taskDocument.getFixedRecurrenceDay())
        .month(taskDocument.getFixedRecurrenceMonth())
        .build();
  }

  private DynamicRecurrence toDynamicRecurrence(final TaskDocument taskDocument) {
    return DynamicRecurrence.builder()
        .dateUnit(taskDocument.getDynamicRecurrencePeriod().getDateUnit())
        .frequency(taskDocument.getDynamicRecurrencePeriod().getFrequency())
        .build();
  }
}
