package rs.laxsrbija.chores.data.repository;

import org.springframework.stereotype.Repository;
import io.jsondb.JsonDBTemplate;
import rs.laxsrbija.chores.data.entity.TaskEntity;

@Repository
public class TaskRepository extends CrudRepository<TaskEntity>
{
	public TaskRepository(final JsonDBTemplate jsonDBTemplate)
	{
		super(jsonDBTemplate, TaskEntity.class);
	}
}
