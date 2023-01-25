package net.lazars.chores.adapter.db.entity.embedded.recurrence;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.lazars.chores.core.model.date.DatePeriod;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DynamicRecurrencePart extends DatePeriod implements RecurrencePart {}
