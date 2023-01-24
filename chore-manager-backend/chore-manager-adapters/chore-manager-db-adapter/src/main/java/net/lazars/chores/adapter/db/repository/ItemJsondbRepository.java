package net.lazars.chores.adapter.db.repository;

import io.jsondb.JsonDBTemplate;
import net.lazars.chores.adapter.db.entity.ItemEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ItemJsondbRepository extends CrudRepository<ItemEntity> {

  public ItemJsondbRepository(final JsonDBTemplate jsonDBTemplate) {
    super(jsonDBTemplate, ItemEntity.class);
  }
}
