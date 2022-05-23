package rs.laxsrbija.chores.domain.model.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import rs.laxsrbija.chores.domain.model.CompletionHistoryItem;
import rs.laxsrbija.chores.domain.model.ReminderInfo;
import rs.laxsrbija.chores.domain.model.recurrence.Recurrence;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
public class Task extends BaseDto {

  private String description;

  private LocalDate dateCreated;

  private Object object;

  private Recurrence recurrence;

  private long daysUntilNextRecurrence;

  private LocalDate nextRecurrence;

  private List<CompletionHistoryItem> history;

  private ReminderInfo reminder;

  private boolean enabled;
}
