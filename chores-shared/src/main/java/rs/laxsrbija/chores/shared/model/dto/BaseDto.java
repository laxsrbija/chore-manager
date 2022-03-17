package rs.laxsrbija.chores.shared.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class BaseDto
{
	private String id;

	private String name;

	private String image;
}
