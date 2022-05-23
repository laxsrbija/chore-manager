package rs.laxsrbija.chores.domain.model.date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum DateUnit {
  DAY(1),
  WEEK(7),
  MONTH(30),
  YEAR(365);

  private final int daysPerUnit;
}
