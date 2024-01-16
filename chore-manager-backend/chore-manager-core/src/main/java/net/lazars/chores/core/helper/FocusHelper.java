package net.lazars.chores.core.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.lazars.chores.core.model.Task;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FocusHelper {

  public static boolean isInFocus(final Task task) {
    final int daysBeforeReminder =
        task.getReminder().getReminderDate().getDateUnit().getDaysPerUnit()
            * task.getReminder().getReminderDate().getFrequency();
    return task.getOccurrence().getDaysUntilNextOccurrence() - daysBeforeReminder <= 0;
  }
}
