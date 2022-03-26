package rs.laxsrbija.chores.shared.model.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.*;
import lombok.experimental.SuperBuilder;
import rs.laxsrbija.chores.shared.model.CompletionHistoryItem;
import rs.laxsrbija.chores.shared.model.ReminderInfo;
import rs.laxsrbija.chores.shared.model.recurrence.Recurrence;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
public class Task extends BaseDto
{
	private LocalDate dateCreated;

	private Object object;

	private Recurrence recurrence;

	private long daysUntilNextRecurrence;

	private LocalDate nextRecurrence;

	private List<CompletionHistoryItem> history;

	private ReminderInfo reminder;

	private boolean enabled;
}
