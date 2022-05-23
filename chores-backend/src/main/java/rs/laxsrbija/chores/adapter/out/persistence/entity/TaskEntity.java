package rs.laxsrbija.chores.adapter.out.persistence.entity;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import rs.laxsrbija.chores.domain.model.CompletionHistoryItem;
import rs.laxsrbija.chores.domain.model.ReminderInfo;
import rs.laxsrbija.chores.domain.model.recurrence.Recurrence;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "tasks", schemaVersion = "1.0")
public class TaskEntity {

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
