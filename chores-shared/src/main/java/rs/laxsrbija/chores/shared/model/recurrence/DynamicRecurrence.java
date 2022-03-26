package rs.laxsrbija.chores.shared.model.recurrence;

import lombok.*;
import lombok.experimental.SuperBuilder;
import rs.laxsrbija.chores.shared.model.date.DatePeriod;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DynamicRecurrence extends DatePeriod implements Recurrence
{

}
