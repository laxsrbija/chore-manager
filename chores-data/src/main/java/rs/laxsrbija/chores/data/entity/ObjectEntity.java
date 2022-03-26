package rs.laxsrbija.chores.data.entity;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "objects", schemaVersion = "1.0")
public class ObjectEntity
{
	@Id
	private String id;

	private String name;

	private String categoryId;
}
