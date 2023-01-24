package net.lazars.chores.adapter.rest.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lazars.chores.core.model.Task;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OverviewDto {

  private List<Task> upcoming;
  private List<Task> overdue;
  private List<Task> disabled;

  private int taskCount;
  private int itemCount;
  private int categoryCount;
  private int userCount;

  private String build;
}
