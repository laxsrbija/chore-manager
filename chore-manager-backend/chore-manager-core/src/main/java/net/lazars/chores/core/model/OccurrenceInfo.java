package net.lazars.chores.core.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OccurrenceInfo {

  private long daysUntilNextOccurrence;

  private LocalDate nextOccurrence;
}
