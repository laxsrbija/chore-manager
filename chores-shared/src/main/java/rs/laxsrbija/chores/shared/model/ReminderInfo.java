package rs.laxsrbija.chores.shared.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import rs.laxsrbija.chores.shared.model.date.DatePeriod;

@Data
@Builder
public class ReminderInfo
{
	private List<String> usersToNotify;
	private DatePeriod reminderDate;
}
