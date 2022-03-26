package rs.laxsrbija.chores.shared.model.date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DatePeriod
{
	private int frequency;
	private DateUnit dateUnit;

	@JsonIgnore
	public int getNumberOfDays()
	{
		return frequency * dateUnit.getDaysPerUnit();
	}
}
