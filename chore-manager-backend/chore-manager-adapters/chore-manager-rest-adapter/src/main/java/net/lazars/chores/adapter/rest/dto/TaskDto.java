package net.lazars.chores.adapter.rest.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.lazars.chores.adapter.rest.dto.recurrence.RecurrenceDto;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto extends BaseDto {

  private String description;

  private LocalDate dateCreated;

  private ItemDto item;

  private RecurrenceDto recurrence;

  private OccurrenceInfoDto occurrence;

  private List<CompletionHistoryItemDto> history;

  private ReminderInfoDto reminder;

  private boolean enabled;
}
