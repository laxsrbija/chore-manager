package net.lazars.chores.adapter.db.mapper;

import net.lazars.chores.adapter.db.entity.CategoryDocument;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.model.Household;
import org.mapstruct.Mapper;import org.mapstruct.Mapping;

@Mapper
public interface CategoryMapper {

  Category toCategory(CategoryDocument category, Household household);

  @Mapping(target = "householdId", source = "household.id")
  CategoryDocument toCategoryDocument(Category category);
}
