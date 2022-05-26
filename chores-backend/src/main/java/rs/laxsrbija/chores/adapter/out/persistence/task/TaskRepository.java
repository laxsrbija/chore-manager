package rs.laxsrbija.chores.adapter.out.persistence.task;

import io.jsondb.JsonDBTemplate;
import org.springframework.stereotype.Repository;
import rs.laxsrbija.chores.adapter.out.persistence.CrudRepository;
import rs.laxsrbija.chores.adapter.out.persistence.entity.TaskEntity;

@Repository
class TaskRepository extends CrudRepository<TaskEntity> {

  public TaskRepository(final JsonDBTemplate jsonDBTemplate) {
    super(jsonDBTemplate, TaskEntity.class);
  }
}
