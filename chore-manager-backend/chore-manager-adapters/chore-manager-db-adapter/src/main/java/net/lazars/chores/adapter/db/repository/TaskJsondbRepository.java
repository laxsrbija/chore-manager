package net.lazars.chores.adapter.db.repository;

import io.jsondb.JsonDBTemplate;
import net.lazars.chores.adapter.db.entity.TaskDocument;
import org.springframework.stereotype.Repository;

@Repository
public class TaskJsondbRepository extends CrudRepository<TaskDocument> {

  public TaskJsondbRepository(final JsonDBTemplate jsonDBTemplate) {
    super(jsonDBTemplate, TaskDocument.class);
  }
}
