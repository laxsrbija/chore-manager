package net.lazars.chores.adapter.redis.service;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.redis.document.CategoryDocument;
import net.lazars.chores.adapter.redis.mapper.CategoryMapper;
import net.lazars.chores.adapter.redis.repository.CategoryRedisRepository;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.port.out.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CategoryRepositoryImpl extends EntityRepository<Category> implements CategoryRepository {

  private final CategoryMapper categoryMapper;
  private final CategoryRedisRepository categoryRedisRepository;

  @Override
  public Category get(final String id) {
    final CategoryDocument categoryEntity = categoryRedisRepository.findById(id).orElseThrow();
    return categoryMapper.toCategory(categoryEntity);
  }

  @Override
  public List<Category> getAll() {
    return forEach(categoryRedisRepository.findAll(), categoryMapper::toCategory);
  }

  @Override
  protected void saveEntity(final Category entity) {
    final CategoryDocument categoryEntity = categoryMapper.toCategoryDocument(entity);
    categoryRedisRepository.save(categoryEntity);
  }

  @Override
  public void delete(final String id) {
    categoryRedisRepository.deleteById(id);
  }
}
