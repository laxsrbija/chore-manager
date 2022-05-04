package rs.laxsrbija.chores.data.entity;

import java.time.LocalDate;
import java.util.List;
import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import lombok.*;
import rs.laxsrbija.chores.shared.model.CompletionHistoryItem;
import rs.laxsrbija.chores.shared.model.ReminderInfo;
import rs.laxsrbija.chores.shared.model.recurrence.Recurrence;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "tasks", schemaVersion = "1.0")
public class TaskEntity
{
	@Id
	private String id;

	private String name;

	private String description;

	private LocalDate dateCreated;

	private String objectId;

	private Recurrence recurrence;

	private List<CompletionHistoryItem> history;

	private ReminderInfo reminder;

	private boolean enabled;
}
