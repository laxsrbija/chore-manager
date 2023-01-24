package net.lazars.chores.adapter.db.repository;

import io.jsondb.JsonDBTemplate;
import net.lazars.chores.adapter.db.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserJsondbRepository extends CrudRepository<UserEntity> {

  public UserJsondbRepository(final JsonDBTemplate jsonDBTemplate) {
    super(jsonDBTemplate, UserEntity.class);
  }
}
