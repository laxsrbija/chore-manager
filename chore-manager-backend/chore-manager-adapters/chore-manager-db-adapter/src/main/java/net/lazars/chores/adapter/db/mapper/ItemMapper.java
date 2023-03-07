package net.lazars.chores.adapter.db.mapper;

import net.lazars.chores.adapter.db.entity.ItemDocument;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ItemMapper {

  Item toItem(ItemDocument itemEntity, Category category);

  @Mapping(target = "categoryId", source = "category.id")
  ItemDocument toItemDocument(Item item);
}
