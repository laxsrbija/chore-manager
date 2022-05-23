package rs.laxsrbija.chores.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.laxsrbija.chores.adapter.out.persistence.entity.CategoryEntity;
import rs.laxsrbija.chores.adapter.out.persistence.mapper.CategoryMapper;
import rs.laxsrbija.chores.adapter.out.persistence.repository.CategoryRepository;
import rs.laxsrbija.chores.domain.model.dto.Category;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository _categoryRepository;
  private final CategoryMapper _categoryMapper;

  public Category getCategory(final String id) {
    final CategoryEntity categoryEntity = _categoryRepository.findById(id);
    return _categoryMapper.toCategory(categoryEntity);
  }
}
