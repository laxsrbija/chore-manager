package rs.laxsrbija.chores.application.util;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import rs.laxsrbija.chores.application.exception.ChoreManagerException;
import rs.laxsrbija.chores.domain.recurrence.DynamicRecurrence;
import rs.laxsrbija.chores.domain.recurrence.FixedRecurrence;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecurrenceUtil {

  public static LocalDate getNextOccurrence(final LocalDate latestCompletion,
      final DynamicRecurrence dynamicRecurrence) {
    if (latestCompletion == null) {
      return LocalDate.now();
    }

    return latestCompletion.plusDays(dynamicRecurrence.getNumberOfDays());
  }

  // day only - every nth day each month
  // month only - every 1st day each month
  // day and month - every year on the exact date
  public static LocalDate getNextOccurrence(
      final LocalDate latestCompletion,
      final FixedRecurrence fixedRecurrence) {
    final Integer day = fixedRecurrence.getDay();
    final Integer month = fixedRecurrence.getMonth();

    final LocalDate nextRun;
    final LocalDate now = LocalDate.now();

    if (day != null && month == null) {
      if (latestCompletion == null) {
        nextRun = LocalDate.of(now.getYear(), now.getMonth(), day);

        if (nextRun.compareTo(now) < 0) {
          return nextRun.plusMonths(1);
        }
      } else {
        nextRun = LocalDate.of(latestCompletion.getYear(), latestCompletion.getMonth(), day);

        if (nextRun.compareTo(latestCompletion) < 0) {
          return nextRun.plusMonths(1);
        }
      }
    } else if (day == null && month != null) {
      if (latestCompletion == null) {
        nextRun = LocalDate.of(now.getYear(), month, 1);

        if (nextRun.compareTo(now) < 0) {
          return nextRun.plusYears(1);
        }
      } else {
        nextRun = LocalDate.of(latestCompletion.getYear(), month, 1);

        if (nextRun.compareTo(latestCompletion) < 0) {
          return nextRun.plusYears(1);
        }
      }
    } else if (day != null) {
      if (latestCompletion == null) {
        nextRun = LocalDate.of(now.getYear(), month, day);

        if (nextRun.compareTo(now) < 0) {
          return nextRun.plusYears(1);
        }
      } else {
        nextRun = LocalDate.of(latestCompletion.getYear(), month, day);

        if (nextRun.compareTo(latestCompletion) < 0) {
          return nextRun.plusYears(1);
        }
      }
    } else {
      throw new ChoreManagerException("Fixed recurrence interval not set");
    }

    return nextRun;
  }
}
