package rs.laxsrbija.chores.adapter.out.persistence.category;

import static rs.laxsrbija.chores.common.Commons.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.adapter.out.persistence.PersistenceAdapter;
import rs.laxsrbija.chores.adapter.out.persistence.entity.CategoryEntity;
import rs.laxsrbija.chores.application.port.out.CategoryOutboundPort;
import rs.laxsrbija.chores.domain.Category;

@Component
@RequiredArgsConstructor
class CategoryPersistenceAdapter extends PersistenceAdapter<Category>
    implements CategoryOutboundPort {

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
  protected void saveEntity(final Category entity) {
    final CategoryEntity categoryEntity = categoryMapper.toCategoryEntity(entity);
    categoryRepository.save(categoryEntity);
  }

  @Override
  public void delete(final String id) {
    categoryRepository.delete(id);
  }
}
