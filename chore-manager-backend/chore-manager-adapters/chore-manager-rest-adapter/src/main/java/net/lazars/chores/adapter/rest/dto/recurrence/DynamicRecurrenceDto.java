package net.lazars.chores.adapter.rest.dto.recurrence;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.lazars.chores.core.model.date.DatePeriod;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DynamicRecurrenceDto extends DatePeriod implements RecurrenceDto {}
