package rs.laxsrbija.chores.data.entity;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "users", schemaVersion = "1.0")
public class UserEntity
{
	@Id
	private String id;

	private String name;

	private String email;
}
