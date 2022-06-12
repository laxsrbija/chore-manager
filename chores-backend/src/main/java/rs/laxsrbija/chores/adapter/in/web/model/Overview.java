package rs.laxsrbija.chores.adapter.in.web.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.laxsrbija.chores.domain.Task;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Overview {

  private List<Task> upcoming;
  private List<Task> overdue;
  private List<Task> disabled;

  private int taskCount;
  private int itemCount;
  private int categoryCount;
  private int userCount;
}
