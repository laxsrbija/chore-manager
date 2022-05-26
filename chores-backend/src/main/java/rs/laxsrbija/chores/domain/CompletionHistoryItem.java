package rs.laxsrbija.chores.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletionHistoryItem {

  private User user;
  private LocalDate dateCompleted;
}
