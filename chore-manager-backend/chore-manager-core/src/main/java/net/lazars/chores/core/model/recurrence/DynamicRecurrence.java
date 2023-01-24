package net.lazars.chores.core.model.recurrence;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.lazars.chores.core.model.date.DatePeriod;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DynamicRecurrence extends DatePeriod implements Recurrence {}
