package rs.laxsrbija.chores.data.repository;

import org.springframework.stereotype.Repository;
import io.jsondb.JsonDBTemplate;
import rs.laxsrbija.chores.data.entity.CategoryEntity;

@Repository
public class CategoryRepository extends CrudRepository<CategoryEntity>
{
	public CategoryRepository(final JsonDBTemplate jsonDBTemplate)
	{
		super(jsonDBTemplate, CategoryEntity.class);
	}
}
