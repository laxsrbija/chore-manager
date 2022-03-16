package rs.laxsrbija.chores.data.repository;

import org.springframework.stereotype.Repository;
import io.jsondb.JsonDBTemplate;
import rs.laxsrbija.chores.data.entity.User;

@Repository
public class UserRepository extends CrudRepository<User>
{
	public UserRepository(final JsonDBTemplate jsonDBTemplate)
	{
		super(jsonDBTemplate, User.class);
	}
}
