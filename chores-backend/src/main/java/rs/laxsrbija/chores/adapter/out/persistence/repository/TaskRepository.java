package rs.laxsrbija.chores.adapter.out.persistence.repository;

import io.jsondb.JsonDBTemplate;
import org.springframework.stereotype.Repository;
import rs.laxsrbija.chores.adapter.out.persistence.entity.TaskEntity;

@Repository
public class TaskRepository extends CrudRepository<TaskEntity> {

  public TaskRepository(final JsonDBTemplate jsonDBTemplate) {
    super(jsonDBTemplate, TaskEntity.class);
  }
}
