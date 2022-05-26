package rs.laxsrbija.chores.adapter.out.persistence.entity.embedded;

import java.util.Set;
import lombok.Builder;
import lombok.Data;
import rs.laxsrbija.chores.domain.date.DatePeriod;

@Data
@Builder
public class ReminderInfoEntity {

  private Set<String> usersIdsToNotify;

  // Days to remind before the actual due date
  private DatePeriod reminderDate;
}
