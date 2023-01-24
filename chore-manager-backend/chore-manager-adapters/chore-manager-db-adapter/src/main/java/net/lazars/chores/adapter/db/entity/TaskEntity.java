package net.lazars.chores.adapter.db.entity;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.lazars.chores.adapter.db.entity.embedded.CompletionHistoryItemEntity;
import net.lazars.chores.adapter.db.entity.embedded.ReminderInfoEntity;
import net.lazars.chores.core.model.recurrence.Recurrence;

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

  private String itemId;

  private Recurrence recurrence;

  private List<CompletionHistoryItemEntity> history;

  private ReminderInfoEntity reminder;

  private boolean enabled;
}
