package rs.laxsrbija.chores.adapter.out.persistence.repository;

import io.jsondb.JsonDBTemplate;
import org.springframework.stereotype.Repository;
import rs.laxsrbija.chores.adapter.out.persistence.entity.UserEntity;

@Repository
public class UserRepository extends CrudRepository<UserEntity> {

  public UserRepository(final JsonDBTemplate jsonDBTemplate) {
    super(jsonDBTemplate, UserEntity.class);
  }
}
