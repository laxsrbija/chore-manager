package net.lazars.chores.core.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.lazars.chores.core.model.recurrence.Recurrence;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

  private UUID id;
  private String name;

  private String description;

  private LocalDate dateCreated;

  private UUID itemId;

  private Recurrence recurrence;

  private OccurrenceInfo occurrence;

  private DeferInfo defer;

  private List<CompletionHistoryItem> history;

  private ReminderInfo reminder;

  private boolean enabled;
}
