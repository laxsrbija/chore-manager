package rs.laxsrbija.chores.adapter.out.persistence.user;

import io.jsondb.JsonDBTemplate;
import org.springframework.stereotype.Repository;
import rs.laxsrbija.chores.adapter.out.persistence.CrudRepository;
import rs.laxsrbija.chores.adapter.out.persistence.entity.UserEntity;

@Repository
class UserRepository extends CrudRepository<UserEntity> {

  public UserRepository(final JsonDBTemplate jsonDBTemplate) {
    super(jsonDBTemplate, UserEntity.class);
  }
}
