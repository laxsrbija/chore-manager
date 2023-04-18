package net.lazars.chores.adapter.redis.document;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeferInfoPart {

  private LocalDate deferDate;
  private String userId;
}
