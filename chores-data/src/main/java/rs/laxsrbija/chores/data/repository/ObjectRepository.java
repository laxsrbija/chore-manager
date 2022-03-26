package rs.laxsrbija.chores.data.repository;

import org.springframework.stereotype.Repository;
import io.jsondb.JsonDBTemplate;
import rs.laxsrbija.chores.data.entity.ObjectEntity;

@Repository
public class ObjectRepository extends CrudRepository<ObjectEntity>
{
	public ObjectRepository(final JsonDBTemplate jsonDBTemplate)
	{
		super(jsonDBTemplate, ObjectEntity.class);
	}
}
