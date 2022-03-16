package rs.laxsrbija.chores.shared.model.recurrence;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import rs.laxsrbija.chores.shared.model.date.DatePeriod;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class DynamicRecurrence extends DatePeriod implements Recurrence
{

}
