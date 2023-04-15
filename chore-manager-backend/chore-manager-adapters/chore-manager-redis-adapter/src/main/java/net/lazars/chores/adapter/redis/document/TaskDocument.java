package net.lazars.chores.adapter.redis.document;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.lazars.chores.core.model.date.DatePeriod;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@AllArgsConstructor
@RedisHash("tasks")
public class TaskDocument {

  @Id private String id;

  private String name;

  private String description;

  private LocalDate dateCreated;

  private String itemId;

  private RecurrenceType recurrenceType;

  private DatePeriod dynamicRecurrencePeriod;

  private Integer fixedRecurrenceDay;
  private Integer fixedRecurrenceMonth;

  private List<CompletionHistoryItemPart> history;

  private ReminderInfoPart reminder;

  private boolean enabled;
}
