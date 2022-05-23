package rs.laxsrbija.chores.domain.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import rs.laxsrbija.chores.domain.model.date.DatePeriod;

@Data
@Builder
public class ReminderInfo {

  private List<String> usersToNotify;

  // Days to remind before the actual due date
  private DatePeriod reminderDate;
}
