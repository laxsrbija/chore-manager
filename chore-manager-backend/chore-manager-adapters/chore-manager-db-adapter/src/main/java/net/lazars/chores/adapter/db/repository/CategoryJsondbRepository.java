package net.lazars.chores.adapter.db.repository;

import io.jsondb.JsonDBTemplate;
import net.lazars.chores.adapter.db.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryJsondbRepository extends CrudRepository<CategoryEntity> {

  public CategoryJsondbRepository(final JsonDBTemplate jsonDBTemplate) {
    super(jsonDBTemplate, CategoryEntity.class);
  }
}
