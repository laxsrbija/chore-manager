package net.lazars.chores.adapter.db.mapper;

import net.lazars.chores.adapter.db.entity.CategoryEntity;
import net.lazars.chores.core.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

  public Category toCategory(final CategoryEntity category) {
    return Category.builder()
        .id(category.getId())
        .name(category.getName())
        .image(category.getImage())
        .build();
  }

  public CategoryEntity toCategoryEntity(final Category category) {
    return CategoryEntity.builder()
        .id(category.getId())
        .name(category.getName())
        .image(category.getImage())
        .build();
  }
}
