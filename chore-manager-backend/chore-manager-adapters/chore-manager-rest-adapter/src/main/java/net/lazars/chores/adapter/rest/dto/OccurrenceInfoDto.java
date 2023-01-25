package net.lazars.chores.adapter.rest.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OccurrenceInfoDto {

  private long daysUntilNextOccurrence;

  private LocalDate nextOccurrence;
}
