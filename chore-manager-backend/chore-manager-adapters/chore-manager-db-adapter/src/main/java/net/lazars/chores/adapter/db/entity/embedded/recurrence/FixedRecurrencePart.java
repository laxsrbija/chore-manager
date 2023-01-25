package net.lazars.chores.adapter.db.entity.embedded.recurrence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FixedRecurrencePart implements RecurrencePart {

  private Integer day;
  private Integer month;
}
