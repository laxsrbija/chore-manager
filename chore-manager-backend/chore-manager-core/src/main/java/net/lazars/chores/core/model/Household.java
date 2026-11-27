package net.lazars.chores.core.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@Builder
@AllArgsConstructor
public class Household {

  private UUID id;
  private String name;
}
