package rs.laxsrbija.chores.application.util;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import rs.laxsrbija.chores.domain.BaseDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerator {

  public static void validateId(final BaseDto baseDto) {
    if (baseDto.getId() == null || baseDto.getId().trim().isEmpty()) {
      final String id = UUID.randomUUID().toString();
      baseDto.setId(id);
    }
  }
}
