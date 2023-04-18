package net.lazars.chores.core.model;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.lazars.chores.core.model.recurrence.Recurrence;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Task extends BaseModel {

  private String description;

  private LocalDate dateCreated;

  private Item item;

  private Recurrence recurrence;

  private OccurrenceInfo occurrence;

  private DeferInfo defer;

  private List<CompletionHistoryItem> history;

  private ReminderInfo reminder;

  private boolean enabled;
}
