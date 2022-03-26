package rs.laxsrbija.chores.shared.model.recurrence;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FixedRecurrence implements Recurrence
{
	private Integer day;
	private Integer month;
}
