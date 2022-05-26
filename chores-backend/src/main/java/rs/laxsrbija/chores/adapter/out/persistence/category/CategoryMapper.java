package rs.laxsrbija.chores.adapter.out.persistence.category;

import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.adapter.out.persistence.entity.CategoryEntity;
import rs.laxsrbija.chores.domain.Category;

@Component
class CategoryMapper {

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
