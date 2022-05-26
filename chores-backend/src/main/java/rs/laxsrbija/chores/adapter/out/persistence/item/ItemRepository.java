package rs.laxsrbija.chores.adapter.out.persistence.item;

import io.jsondb.JsonDBTemplate;
import org.springframework.stereotype.Repository;
import rs.laxsrbija.chores.adapter.out.persistence.CrudRepository;
import rs.laxsrbija.chores.adapter.out.persistence.entity.ItemEntity;

@Repository
class ItemRepository extends CrudRepository<ItemEntity> {

  public ItemRepository(final JsonDBTemplate jsonDBTemplate) {
    super(jsonDBTemplate, ItemEntity.class);
  }
}
