package net.lazars.chores.core.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Category {

  private UUID id;
  private String name;
  private UUID householdId;
}
