package net.lazars.chores.adapter.rest.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OverviewDto {

  private List<TaskDto> overdue;
  private List<TaskDto> upcoming;
}
