package rs.laxsrbija.chores.domain;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import rs.laxsrbija.chores.domain.date.DatePeriod;

@Data
@Builder
public class ReminderInfo {

  private List<String> usersToNotify;

  // Days to remind before the actual due date
  private DatePeriod reminderDate;
}
