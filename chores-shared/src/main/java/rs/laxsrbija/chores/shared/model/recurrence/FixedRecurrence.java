package rs.laxsrbija.chores.shared.model.recurrence;

import java.time.LocalDate;
import lombok.Data;

@Data
public class FixedRecurrence implements Recurrence
{
	private LocalDate taskDate;
}
