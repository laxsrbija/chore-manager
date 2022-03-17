package rs.laxsrbija.chores.shared.model.recurrence;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FixedRecurrence implements Recurrence
{
	private Integer day;
	private Integer month;
}
