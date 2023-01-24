package net.lazars.chores.adapter.db.entity.embedded;

import java.util.Set;
import lombok.Builder;
import lombok.Data;
import net.lazars.chores.core.model.date.DatePeriod;

@Data
@Builder
public class ReminderInfoEntity {

  private Set<String> usersIdsToNotify;

  // Days to remind before the actual due date
  private DatePeriod reminderDate;
}
