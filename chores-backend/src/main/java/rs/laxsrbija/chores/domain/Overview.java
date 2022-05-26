package rs.laxsrbija.chores.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Overview {

  private List<Task> upcoming;
  private List<Task> overdue;
}
