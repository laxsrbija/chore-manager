package net.lazars.chores.core.model;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import net.lazars.chores.core.model.date.DatePeriod;

@Data
@Builder
public class ReminderInfo {

  private List<UUID> usersToNotify;

  // Days to remind before the actual due date
  private DatePeriod reminderDate;
}
