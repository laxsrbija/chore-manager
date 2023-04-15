package net.lazars.chores.adapter.redis.mapper;

import net.lazars.chores.adapter.redis.document.ItemDocument;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ItemMapper {

  @Mapping(target = "id", source = "itemDocument.id")
  @Mapping(target = "name", source = "itemDocument.name")
  Item toItem(ItemDocument itemDocument, Category category);

  @Mapping(target = "categoryId", source = "category.id")
  ItemDocument toItemDocument(Item item);
}
