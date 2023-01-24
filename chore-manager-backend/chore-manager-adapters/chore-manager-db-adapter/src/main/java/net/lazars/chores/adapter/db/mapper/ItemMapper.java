package net.lazars.chores.adapter.db.mapper;

import net.lazars.chores.adapter.db.entity.ItemEntity;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

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
