package net.lazars.chores.adapter.db.mapper;

import net.lazars.chores.adapter.db.entity.ItemDocument;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

  public Item toItem(final ItemDocument itemEntity, final Category category) {
    return Item.builder()
        .id(itemEntity.getId())
        .name(itemEntity.getName())
        .image(itemEntity.getImage())
        .category(category)
        .build();
  }

  public ItemDocument toItemDocument(final Item item) {
    return ItemDocument.builder()
        .id(item.getId())
        .name(item.getName())
        .image(item.getImage())
        .categoryId(item.getCategory().getId())
        .build();
  }
}
