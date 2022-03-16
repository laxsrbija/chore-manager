package rs.laxsrbija.chores.data.entity;

import io.jsondb.annotation.Id;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class BaseEntity
{
	@Id
	private String id;

	private String name;

	private String image;
}
