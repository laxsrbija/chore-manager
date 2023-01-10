package rs.laxsrbija.chores.application.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import rs.laxsrbija.chores.application.exception.ChoreManagerException;
import rs.laxsrbija.chores.domain.Task;
import rs.laxsrbija.chores.domain.recurrence.DynamicRecurrence;
import rs.laxsrbija.chores.domain.recurrence.FixedRecurrence;
import rs.laxsrbija.chores.domain.recurrence.Recurrence;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OccurrenceUtil {

  public static LocalDate getNextOccurrence(final Task task) {
    final LocalDate latestCompletion =
        task.getHistory() != null && !task.getHistory().isEmpty()
            ? task.getHistory().get(task.getHistory().size() - 1).getDateCompleted()
            : null;

    final Recurrence recurrence = task.getRecurrence();
    if (recurrence instanceof DynamicRecurrence dynamicRecurrence) {
      return getNextOccurrence(latestCompletion, dynamicRecurrence);
    } else {
      return getNextOccurrence(latestCompletion, (FixedRecurrence) recurrence);
    }
  }

  public static long getDaysUntilNextOccurrence(final Task task) {
    final LocalDate nextOccurrence = getNextOccurrence(task);
    return ChronoUnit.DAYS.between(LocalDate.now(), nextOccurrence);
  }

  private static LocalDate getNextOccurrence(
      final LocalDate latestCompletion, final DynamicRecurrence dynamicRecurrence) {
    if (latestCompletion == null) {
      return LocalDate.now();
    }

    return latestCompletion.plusDays(dynamicRecurrence.getNumberOfDays());
  }

  private static LocalDate getNextOccurrence(
      final LocalDate latestCompletion, final FixedRecurrence fixedRecurrence) {
    final Integer day = fixedRecurrence.getDay();
    final Integer month = fixedRecurrence.getMonth();

    if (day != null && month == null) {
      // day only - every nth day each month
      return handleTasksRecurringOnSpecificDays(latestCompletion, day);
    } else if (day == null && month != null) {
      // month only - every 1st day each month
      return handleTasksRecurringOnSpecificMonths(latestCompletion, month);
    } else if (day != null) {
      // day and month - every year on the exact date
      return handleTasksRecurringOnSpecificDates(latestCompletion, day, month);
    } else {
      throw new ChoreManagerException("Fixed recurrence interval not set");
    }
  }

  private static LocalDate handleTasksRecurringOnSpecificDays(
      final LocalDate latestCompletion, final Integer day) {
    final LocalDate nextRun;
    final LocalDate now = LocalDate.now();

    if (latestCompletion == null) {
      nextRun = LocalDate.of(now.getYear(), now.getMonth(), day);

      if (nextRun.isBefore(now)) {
        return LocalDate.of(now.getYear(), now.getMonth().plus(1), day);
      }
    } else {
      nextRun = LocalDate.of(latestCompletion.getYear(), latestCompletion.getMonth().plus(1), day);

      if (nextRun.isBefore(latestCompletion)) {
        return nextRun.plusMonths(1);
      }
    }

    return nextRun;
  }

  private static LocalDate handleTasksRecurringOnSpecificMonths(
      final LocalDate latestCompletion, final Integer month) {
    final LocalDate nextRun;
    final LocalDate now = LocalDate.now();

    if (latestCompletion == null) {
      nextRun = LocalDate.of(now.getYear(), month, 1);

      if (nextRun.isBefore(now)) {
        return LocalDate.of(now.getYear() + 1, month, 1);
      }
    } else {
      nextRun = LocalDate.of(latestCompletion.getYear(), month, 1);

      if (nextRun.isBefore(latestCompletion)) {
        return LocalDate.of(latestCompletion.getYear() + 1, month, 1);
      }
    }

    return nextRun;
  }

  private static LocalDate handleTasksRecurringOnSpecificDates(
      final LocalDate latestCompletion, final Integer day, final Integer month) {
    final LocalDate nextRun;
    final LocalDate now = LocalDate.now();

    if (latestCompletion == null) {
      nextRun = LocalDate.of(now.getYear(), month, day);

      if (nextRun.isBefore(now)) {
        return nextRun.plusYears(1);
      }
    } else {
      return LocalDate.of(latestCompletion.getYear() + 1, month, day);
    }

    return nextRun;
  }
}
