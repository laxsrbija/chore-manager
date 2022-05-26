package rs.laxsrbija.chores.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Overview {

  private List<Task> upcoming;
  private List<Task> overdue;
}
