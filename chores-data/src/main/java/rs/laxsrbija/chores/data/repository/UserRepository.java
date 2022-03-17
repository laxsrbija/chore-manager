package rs.laxsrbija.chores.data.repository;

import org.springframework.stereotype.Repository;
import io.jsondb.JsonDBTemplate;
import rs.laxsrbija.chores.data.entity.UserEntity;

@Repository
public class UserRepository extends CrudRepository<UserEntity>
{
	public UserRepository(final JsonDBTemplate jsonDBTemplate)
	{
		super(jsonDBTemplate, UserEntity.class);
	}
}
