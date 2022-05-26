package rs.laxsrbija.chores.application.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
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
    if (recurrence instanceof DynamicRecurrence) {
      return RecurrenceUtil.getNextOccurrence(latestCompletion, (DynamicRecurrence) recurrence);
    } else {
      return RecurrenceUtil.getNextOccurrence(latestCompletion, (FixedRecurrence) recurrence);
    }
  }

  public static long getDaysUntilNextOccurrence(final Task task) {
    final LocalDate nextOccurrence = getNextOccurrence(task);
    return ChronoUnit.DAYS.between(LocalDate.now(), nextOccurrence);
  }
}
