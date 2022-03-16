package rs.laxsrbija.chores.shared.model.date;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class DatePeriod
{
	private int frequency;
	private DateUnit dateUnit;

	public int getNumberOfDays()
	{
		return frequency * dateUnit.getDaysPerUnit();
	}
}
