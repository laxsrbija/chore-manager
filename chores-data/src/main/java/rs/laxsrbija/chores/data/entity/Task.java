package rs.laxsrbija.chores.data.entity;

import java.util.List;
import io.jsondb.annotation.Document;
import lombok.*;
import lombok.experimental.SuperBuilder;
import rs.laxsrbija.chores.shared.model.CompletionHistoryItem;
import rs.laxsrbija.chores.shared.model.ReminderInfo;
import rs.laxsrbija.chores.shared.model.recurrence.Recurrence;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@Document(collection = "tasks", schemaVersion = "1.0")
public class Task extends BaseEntity
{
	private Recurrence recurrence;

	private List<CompletionHistoryItem> history;

	private ReminderInfo reminder;
}
