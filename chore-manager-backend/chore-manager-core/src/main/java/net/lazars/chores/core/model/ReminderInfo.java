package net.lazars.chores.core.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import net.lazars.chores.core.model.date.DatePeriod;

@Data
@Builder
public class ReminderInfo {

  private List<User> usersToNotify;

  // Days to remind before the actual due date
  private DatePeriod reminderDate;
}
