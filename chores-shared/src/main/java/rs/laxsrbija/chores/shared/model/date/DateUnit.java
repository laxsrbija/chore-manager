package rs.laxsrbija.chores.shared.model.date;

import lombok.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum DateUnit
{
	DAY(1),
	WEEK(7),
	MONTH(30),
	YEAR(365);

	private final int daysPerUnit;
}
