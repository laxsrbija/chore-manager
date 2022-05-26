package rs.laxsrbija.chores.adapter.out.persistence.category;

import static rs.laxsrbija.chores.common.Commons.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.adapter.out.persistence.entity.CategoryEntity;
import rs.laxsrbija.chores.application.port.out.CategoryOutboundPort;
import rs.laxsrbija.chores.application.util.IdGenerator;
import rs.laxsrbija.chores.domain.Category;

@Component
@RequiredArgsConstructor
class CategoryPersistenceAdapter implements CategoryOutboundPort {

  private CategoryMapper categoryMapper;
  private CategoryRepository categoryRepository;

  @Override
  public Category get(final String id) {
    final CategoryEntity categoryEntity = categoryRepository.findById(id);
    return categoryMapper.toCategory(categoryEntity);
  }

  @Override
  public List<Category> getAll() {
    return forEach(categoryRepository.findAll(), categoryMapper::toCategory);
  }

  @Override
  public Category save(final Category category) {
    IdGenerator.validateId(category);

    final CategoryEntity categoryEntity = categoryMapper.toCategoryEntity(category);
    categoryRepository.save(categoryEntity);

    return get(category.getId());
  }

  @Override
  public void delete(final String id) {
    categoryRepository.delete(id);
  }
}
