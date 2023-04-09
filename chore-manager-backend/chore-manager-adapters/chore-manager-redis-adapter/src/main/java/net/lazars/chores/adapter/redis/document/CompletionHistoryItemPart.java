package net.lazars.chores.adapter.redis.document;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletionHistoryItemPart {

  private String userId;
  private LocalDate dateCompleted;
}
