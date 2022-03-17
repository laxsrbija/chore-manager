package rs.laxsrbija.chores.data.entity;

import io.jsondb.annotation.Document;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@Document(collection = "objects", schemaVersion = "1.0")
public class ObjectEntity extends BaseEntity
{
	private String categoryId;
}
