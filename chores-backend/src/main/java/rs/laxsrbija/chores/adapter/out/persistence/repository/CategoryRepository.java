package rs.laxsrbija.chores.adapter.out.persistence.repository;

import io.jsondb.JsonDBTemplate;
import org.springframework.stereotype.Repository;
import rs.laxsrbija.chores.adapter.out.persistence.entity.CategoryEntity;

@Repository
public class CategoryRepository extends CrudRepository<CategoryEntity> {

  public CategoryRepository(final JsonDBTemplate jsonDBTemplate) {
    super(jsonDBTemplate, CategoryEntity.class);
  }
}
