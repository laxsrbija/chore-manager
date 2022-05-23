package rs.laxsrbija.chores.adapter.out.persistence.repository;

import io.jsondb.JsonDBTemplate;
import org.springframework.stereotype.Repository;
import rs.laxsrbija.chores.adapter.out.persistence.entity.ObjectEntity;

@Repository
public class ObjectRepository extends CrudRepository<ObjectEntity> {

  public ObjectRepository(final JsonDBTemplate jsonDBTemplate) {
    super(jsonDBTemplate, ObjectEntity.class);
  }
}
