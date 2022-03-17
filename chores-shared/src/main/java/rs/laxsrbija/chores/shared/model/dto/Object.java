package rs.laxsrbija.chores.shared.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
public class Object extends BaseDto
{
	private String categoryId;
}
