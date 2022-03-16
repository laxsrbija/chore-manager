package rs.laxsrbija.chores.data.repository;

import org.springframework.stereotype.Repository;
import io.jsondb.JsonDBTemplate;
import rs.laxsrbija.chores.data.entity.Task;

@Repository
public class TaskRepository extends CrudRepository<Task>
{
	public TaskRepository(final JsonDBTemplate jsonDBTemplate)
	{
		super(jsonDBTemplate, Task.class);
	}
}
