package net.lazars.chores.adapter.sqlite.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.sqlite.entity.CategoryEntity;
import net.lazars.chores.adapter.sqlite.entity.CompletionHistoryItemEntity;
import net.lazars.chores.adapter.sqlite.entity.DatePeriodEntity;
import net.lazars.chores.adapter.sqlite.entity.DeferInfoEntity;
import net.lazars.chores.adapter.sqlite.entity.HouseholdEntity;
import net.lazars.chores.adapter.sqlite.entity.ItemEntity;
import net.lazars.chores.adapter.sqlite.entity.RecurrenceType;
import net.lazars.chores.adapter.sqlite.entity.TaskEntity;
import net.lazars.chores.adapter.sqlite.entity.UserEntity;
import net.lazars.chores.adapter.sqlite.repository.CategoryJpaRepository;
import net.lazars.chores.adapter.sqlite.repository.HouseholdJpaRepository;
import net.lazars.chores.adapter.sqlite.repository.ItemJpaRepository;
import net.lazars.chores.adapter.sqlite.repository.UserJpaRepository;
import net.lazars.chores.core.helper.OccurrenceHelper;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.model.CompletionHistoryItem;
import net.lazars.chores.core.model.DeferInfo;
import net.lazars.chores.core.model.Household;
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
@RequiredArgsConstructor
public class EntityMapper {

  private final HouseholdJpaRepository householdRepository;
  private final CategoryJpaRepository categoryRepository;
  private final ItemJpaRepository itemRepository;
  private final UserJpaRepository userRepository;

  public HouseholdEntity toHouseholdEntity(final Household household) {
    return HouseholdEntity.builder().id(household.getId()).name(household.getName()).build();
  }

  public Household toHousehold(final HouseholdEntity entity) {
    return Household.builder().id(entity.getId()).name(entity.getName()).build();
  }

  public CategoryEntity toCategoryEntity(final Category category) {
    return CategoryEntity.builder()
        .id(category.getId())
        .name(category.getName())
        .household(
            category.getHousehold() == null
                ? null
                : householdRepository.getReferenceById(category.getHousehold().getId()))
        .build();
  }

  public Category toCategory(final CategoryEntity entity) {
    return Category.builder()
        .id(entity.getId())
        .name(entity.getName())
        .household(entity.getHousehold() == null ? null : toHousehold(entity.getHousehold()))
        .build();
  }

  public ItemEntity toItemEntity(final Item item) {
    return ItemEntity.builder()
        .id(item.getId())
        .name(item.getName())
        .category(
            item.getCategory() == null
                ? null
                : categoryRepository.getReferenceById(item.getCategory().getId()))
        .build();
  }

  public Item toItem(final ItemEntity entity) {
    return Item.builder()
        .id(entity.getId())
        .name(entity.getName())
        .category(entity.getCategory() == null ? null : toCategory(entity.getCategory()))
        .build();
  }

  public UserEntity toUserEntity(final User user) {
    return UserEntity.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .encodedPassword(user.getEncodedPassword())
        .image(user.getImage())
        .households(
            listOrEmpty(user.getHouseholds()).stream()
                .map(household -> householdRepository.getReferenceById(household.getId()))
                .collect(Collectors.toSet()))
        .permissions(listOrEmpty(user.getPermissions()).stream().collect(Collectors.toSet()))
        .chanifyToken(user.getChanifyToken())
        .notificationChannels(
            listOrEmpty(user.getNotificationChannels()).stream().collect(Collectors.toSet()))
        .build();
  }

  public User toUser(final UserEntity entity) {
    return User.builder()
        .id(entity.getId())
        .name(entity.getName())
        .email(entity.getEmail())
        .encodedPassword(entity.getEncodedPassword())
        .image(entity.getImage())
        .households(
            listOrEmpty(entity.getHouseholds()).stream().map(this::toHousehold).toList())
        .permissions(listOrEmpty(entity.getPermissions()))
        .chanifyToken(entity.getChanifyToken())
        .notificationChannels(listOrEmpty(entity.getNotificationChannels()))
        .build();
  }

  public TaskEntity toTaskEntity(final Task task) {
    final Recurrence recurrence = task.getRecurrence();
    final TaskEntity.TaskEntityBuilder builder =
        TaskEntity.builder()
            .id(task.getId())
            .name(task.getName())
            .description(task.getDescription())
            .dateCreated(task.getDateCreated() == null ? LocalDate.now() : task.getDateCreated())
            .item(
                task.getItem() == null
                    ? null
                    : itemRepository.getReferenceById(task.getItem().getId()))
            .history(
                listOrEmpty(task.getHistory()).stream()
                    .map(this::toCompletionHistoryItemEntity)
                    .collect(Collectors.toSet()))
            .usersToNotify(
                task.getReminder() == null || task.getReminder().getUsersToNotify() == null
                    ? Set.of()
                    : task.getReminder().getUsersToNotify().stream()
                        .map(user -> userRepository.getReferenceById(user.getId()))
                        .collect(Collectors.toSet()))
            .reminderDate(
                task.getReminder() == null
                    ? null
                    : toDatePeriodEntity(task.getReminder().getReminderDate()))
            .defer(toDeferInfoEntity(task.getDefer()))
            .enabled(task.isEnabled());

    if (recurrence instanceof FixedRecurrence fixedRecurrence) {
      builder
          .recurrenceType(RecurrenceType.FIXED)
          .fixedRecurrenceDay(fixedRecurrence.getDay())
          .fixedRecurrenceMonth(fixedRecurrence.getMonth());
    } else if (recurrence instanceof DynamicRecurrence dynamicRecurrence) {
      builder
          .recurrenceType(RecurrenceType.DYNAMIC)
          .dynamicRecurrencePeriod(toDatePeriodEntity(dynamicRecurrence));
    }

    return builder.build();
  }

  public Task toTask(final TaskEntity entity) {
    final Recurrence recurrence = toRecurrence(entity);
    final List<CompletionHistoryItem> history =
        listOrEmpty(entity.getHistory()).stream().map(this::toCompletionHistoryItem).toList();
    final ReminderInfo reminder =
        entity.getReminderDate() == null && listOrEmpty(entity.getUsersToNotify()).isEmpty()
            ? null
            : ReminderInfo.builder()
                .usersToNotify(
                    listOrEmpty(entity.getUsersToNotify()).stream().map(this::toUser).toList())
                .reminderDate(toDatePeriod(entity.getReminderDate()))
                .build();
    final Task task =
        Task.builder()
            .id(entity.getId())
            .name(entity.getName())
            .description(entity.getDescription())
            .dateCreated(entity.getDateCreated())
            .item(entity.getItem() == null ? null : toItem(entity.getItem()))
            .recurrence(recurrence)
            .defer(toDeferInfo(entity.getDefer()))
            .history(history)
            .reminder(reminder)
            .enabled(entity.isEnabled())
            .build();

    if (recurrence != null) {
      task.setOccurrence(
          OccurrenceInfo.builder()
              .daysUntilNextOccurrence(OccurrenceHelper.getDaysUntilNextOccurrence(task))
              .nextOccurrence(OccurrenceHelper.getNextOccurrence(task))
              .build());
    }
    return task;
  }

  private CompletionHistoryItemEntity toCompletionHistoryItemEntity(
      final CompletionHistoryItem item) {
    return CompletionHistoryItemEntity.builder()
        .user(userRepository.getReferenceById(item.getUser().getId()))
        .dateCompleted(item.getDateCompleted())
        .build();
  }

  private CompletionHistoryItem toCompletionHistoryItem(final CompletionHistoryItemEntity entity) {
    return CompletionHistoryItem.builder()
        .user(toUser(entity.getUser()))
        .dateCompleted(entity.getDateCompleted())
        .build();
  }

  private DeferInfoEntity toDeferInfoEntity(final DeferInfo deferInfo) {
    if (deferInfo == null) {
      return null;
    }
    return DeferInfoEntity.builder()
        .deferDate(deferInfo.getDeferDate())
        .user(
            deferInfo.getUser() == null
                ? null
                : userRepository.getReferenceById(deferInfo.getUser().getId()))
        .build();
  }

  private DeferInfo toDeferInfo(final DeferInfoEntity entity) {
    if (entity == null) {
      return null;
    }
    return DeferInfo.builder()
        .deferDate(entity.getDeferDate())
        .user(entity.getUser() == null ? null : toUser(entity.getUser()))
        .build();
  }

  private DatePeriodEntity toDatePeriodEntity(final DatePeriod datePeriod) {
    if (datePeriod == null) {
      return null;
    }
    return DatePeriodEntity.builder()
        .frequency(datePeriod.getFrequency())
        .dateUnit(datePeriod.getDateUnit())
        .build();
  }

  private DatePeriod toDatePeriod(final DatePeriodEntity entity) {
    if (entity == null) {
      return null;
    }
    return DatePeriod.builder().frequency(entity.getFrequency()).dateUnit(entity.getDateUnit()).build();
  }

  private Recurrence toRecurrence(final TaskEntity entity) {
    if (entity.getRecurrenceType() == RecurrenceType.FIXED) {
      return FixedRecurrence.builder()
          .day(entity.getFixedRecurrenceDay())
          .month(entity.getFixedRecurrenceMonth())
          .build();
    }
    if (entity.getRecurrenceType() == RecurrenceType.DYNAMIC) {
      final DatePeriodEntity period = entity.getDynamicRecurrencePeriod();
      return period == null
          ? null
          : DynamicRecurrence.builder()
              .frequency(period.getFrequency())
              .dateUnit(period.getDateUnit())
              .build();
    }
    return null;
  }

  private <T> List<T> listOrEmpty(final List<T> values) {
    return values == null ? List.of() : values;
  }

  private <T> List<T> listOrEmpty(final Set<T> values) {
    return values == null ? List.of() : List.copyOf(values);
  }
}