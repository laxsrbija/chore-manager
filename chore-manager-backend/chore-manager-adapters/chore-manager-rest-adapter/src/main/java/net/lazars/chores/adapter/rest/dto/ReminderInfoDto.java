package net.lazars.chores.adapter.rest.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import net.lazars.chores.core.model.date.DatePeriod;

@Data
@Builder
public class ReminderInfoDto {

  private List<UserDto> usersToNotify;

  // Days to remind before the actual due date
  private DatePeriod reminderDate;
}
