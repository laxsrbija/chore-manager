package net.lazars.chores.adapter.db.repository;

import io.jsondb.JsonDBTemplate;
import net.lazars.chores.adapter.db.entity.CategoryDocument;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryJsondbRepository extends CrudRepository<CategoryDocument> {

  public CategoryJsondbRepository(final JsonDBTemplate jsonDBTemplate) {
    super(jsonDBTemplate, CategoryDocument.class);
  }
}
