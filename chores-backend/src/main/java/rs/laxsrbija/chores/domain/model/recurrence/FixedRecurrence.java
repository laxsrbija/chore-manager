package rs.laxsrbija.chores.domain.model.recurrence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FixedRecurrence implements Recurrence {

  private Integer day;
  private Integer month;
}
