package net.lazars.chores.adapter.redis.util;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.lazars.chores.core.model.BaseModel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerator {

  public static void validateId(final BaseModel baseDto) {
    if (baseDto.getId() == null || baseDto.getId().trim().isEmpty()) {
      final String id = UUID.randomUUID().toString();
      baseDto.setId(id);
    }
  }
}
