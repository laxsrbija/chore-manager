package net.lazars.chores.adapter.sqlite.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lazars.chores.core.model.date.DateUnit;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DatePeriodEntity {

  private Integer frequency;

  @Enumerated(EnumType.STRING)
  private DateUnit dateUnit;
}
