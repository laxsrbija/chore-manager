package rs.laxsrbija.chores.domain.recurrence;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import rs.laxsrbija.chores.domain.date.DatePeriod;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DynamicRecurrence extends DatePeriod implements Recurrence {

}
