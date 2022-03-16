package rs.laxsrbija.chores.data.entity;

import io.jsondb.annotation.Document;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users", schemaVersion = "1.0")
public class User extends BaseEntity
{
	private String name;
}
