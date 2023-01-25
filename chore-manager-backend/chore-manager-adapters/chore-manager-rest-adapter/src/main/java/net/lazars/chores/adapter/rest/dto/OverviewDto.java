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

  private List<TaskDto> upcoming;
  private List<TaskDto> overdue;
  private List<TaskDto> disabled;

  private int taskCount;
  private int itemCount;
  private int categoryCount;
  private int userCount;

  private String build;
}
