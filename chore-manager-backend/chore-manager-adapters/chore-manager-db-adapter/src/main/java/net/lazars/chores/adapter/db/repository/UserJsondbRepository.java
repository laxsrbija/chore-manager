package net.lazars.chores.adapter.db.repository;

import io.jsondb.JsonDBTemplate;
import net.lazars.chores.adapter.db.entity.UserDocument;
import org.springframework.stereotype.Repository;

@Repository
public class UserJsondbRepository extends CrudRepository<UserDocument> {

  public UserJsondbRepository(final JsonDBTemplate jsonDBTemplate) {
    super(jsonDBTemplate, UserDocument.class);
  }
}
