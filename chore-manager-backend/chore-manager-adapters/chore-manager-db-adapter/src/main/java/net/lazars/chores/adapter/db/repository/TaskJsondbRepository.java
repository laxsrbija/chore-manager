package net.lazars.chores.adapter.db.repository;

import io.jsondb.JsonDBTemplate;
import net.lazars.chores.adapter.db.entity.TaskEntity;
import org.springframework.stereotype.Repository;

@Repository
public class TaskJsondbRepository extends CrudRepository<TaskEntity> {

  public TaskJsondbRepository(final JsonDBTemplate jsonDBTemplate) {
    super(jsonDBTemplate, TaskEntity.class);
  }
}
