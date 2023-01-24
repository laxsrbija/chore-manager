package net.lazars.chores.adapter.db.entity.embedded;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletionHistoryItemEntity {

  private String userId;
  private LocalDate dateCompleted;
}
