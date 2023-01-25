package net.lazars.chores.adapter.db.entity;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.lazars.chores.adapter.db.entity.embedded.CompletionHistoryItemPart;
import net.lazars.chores.adapter.db.entity.embedded.ReminderInfoPart;
import net.lazars.chores.adapter.db.entity.embedded.recurrence.RecurrencePart;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "tasks", schemaVersion = "1.0")
public class TaskDocument {

  @Id
  private String id;

  private String name;

  private String description;

  private LocalDate dateCreated;

  private String itemId;

  private RecurrencePart recurrence;

  private List<CompletionHistoryItemPart> history;

  private ReminderInfoPart reminder;

  private boolean enabled;
}
