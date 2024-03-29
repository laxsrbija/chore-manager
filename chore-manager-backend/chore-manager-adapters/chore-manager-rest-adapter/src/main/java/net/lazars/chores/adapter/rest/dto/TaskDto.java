package net.lazars.chores.adapter.rest.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.lazars.chores.adapter.rest.dto.recurrence.RecurrenceDto;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto extends BaseDto {

  private String description;

  private LocalDate dateCreated;

  private ItemDto item;

  private RecurrenceDto recurrence;

  private OccurrenceInfoDto occurrence;

  private DeferInfoDto defer;

  private List<CompletionHistoryItemDto> history;

  private ReminderInfoDto reminder;

  private boolean enabled;
}
