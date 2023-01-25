package net.lazars.chores.adapter.db.repository;

import io.jsondb.JsonDBTemplate;
import net.lazars.chores.adapter.db.entity.ItemDocument;
import org.springframework.stereotype.Repository;

@Repository
public class ItemJsondbRepository extends CrudRepository<ItemDocument> {

  public ItemJsondbRepository(final JsonDBTemplate jsonDBTemplate) {
    super(jsonDBTemplate, ItemDocument.class);
  }
}
