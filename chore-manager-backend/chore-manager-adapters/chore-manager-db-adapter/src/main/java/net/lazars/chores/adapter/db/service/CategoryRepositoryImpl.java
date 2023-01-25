package net.lazars.chores.adapter.db.service;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.db.entity.CategoryDocument;
import net.lazars.chores.adapter.db.mapper.CategoryMapper;
import net.lazars.chores.adapter.db.repository.CategoryJsondbRepository;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.port.out.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CategoryRepositoryImpl extends EntityRepository<Category>
    implements CategoryRepository {

  private final CategoryMapper categoryMapper;
  private final CategoryJsondbRepository categoryJsondbRepository;

  @Override
  public Category get(final String id) {
    final CategoryDocument categoryEntity = categoryJsondbRepository.findById(id);
    return categoryMapper.toCategory(categoryEntity);
  }

  @Override
  public List<Category> getAll() {
    return forEach(categoryJsondbRepository.findAll(), categoryMapper::toCategory);
  }

  @Override
  protected void saveEntity(final Category entity) {
    final CategoryDocument categoryEntity = categoryMapper.toCategoryDocument(entity);
    categoryJsondbRepository.save(categoryEntity);
  }

  @Override
  public void delete(final String id) {
    categoryJsondbRepository.delete(id);
  }
}
