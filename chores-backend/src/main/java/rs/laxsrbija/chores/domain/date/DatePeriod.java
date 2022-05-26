package rs.laxsrbija.chores.domain.date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DatePeriod {

  private int frequency;
  private DateUnit dateUnit;

  @JsonIgnore
  public int getNumberOfDays() {
    return frequency * dateUnit.getDaysPerUnit();
  }
}
