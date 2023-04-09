package net.lazars.chores.adapter.redis.mapper;

import net.lazars.chores.adapter.redis.document.CategoryDocument;
import net.lazars.chores.core.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

  public Category toCategory(final CategoryDocument category) {
    return Category.builder()
        .id(category.getId())
        .name(category.getName())
        .image(category.getImage())
        .build();
  }

  public CategoryDocument toCategoryDocument(final Category category) {
    return CategoryDocument.builder()
        .id(category.getId())
        .name(category.getName())
        .image(category.getImage())
        .build();
  }
}
