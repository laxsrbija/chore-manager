package net.lazars.chores.adapter.rest.dto.recurrence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FixedRecurrenceDto implements RecurrenceDto {

  private Integer day;
  private Integer month;
}
