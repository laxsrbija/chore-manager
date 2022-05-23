package rs.laxsrbija.chores.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.adapter.out.persistence.entity.CategoryEntity;
import rs.laxsrbija.chores.domain.model.dto.Category;

@Component
public class CategoryMapper {

  public Category toCategory(final CategoryEntity category) {
    return Category.builder()
        .id(category.getId())
        .name(category.getName())
        .build();
  }
}
