package rs.laxsrbija.chores.adapter.out.persistence.item;

import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.adapter.out.persistence.entity.ItemEntity;
import rs.laxsrbija.chores.domain.Category;
import rs.laxsrbija.chores.domain.Item;

@Component
class ItemMapper {

  public Item toItem(final ItemEntity itemEntity, final Category category) {
    return Item.builder()
        .id(itemEntity.getId())
        .name(itemEntity.getName())
        .image(itemEntity.getImage())
        .category(category)
        .build();
  }

  public ItemEntity toItemEntity(final Item item) {
    return ItemEntity.builder()
        .id(item.getId())
        .name(item.getName())
        .image(item.getImage())
        .categoryId(item.getCategory().getId())
        .build();
  }
}
