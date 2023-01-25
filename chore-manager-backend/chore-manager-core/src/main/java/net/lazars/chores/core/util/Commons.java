package net.lazars.chores.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.lazars.chores.core.model.date.DatePeriod;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Commons {

  public static int getNumberOfDays(final DatePeriod datePeriod) {
    return datePeriod.getDateUnit().getDaysPerUnit() * datePeriod.getFrequency();
  }
}
