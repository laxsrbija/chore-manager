package net.lazars.chores.adapter.redis.service;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.redis.document.CategoryDocument;
import net.lazars.chores.adapter.redis.mapper.CategoryMapper;
import net.lazars.chores.adapter.redis.repository.CategoryRedisRepository;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.port.in.HouseholdService;
import net.lazars.chores.core.port.out.CategoryRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CategoryRepositoryImpl extends EntityRepository<Category> implements CategoryRepository {

  private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
  private final CategoryRedisRepository categoryRedisRepository;
  private final HouseholdService householdService;

  @Override
  public Category get(final String id) {
    final CategoryDocument categoryEntity = categoryRedisRepository.findById(id).orElseThrow();
    final Household household = householdService.get(categoryEntity.getHouseholdId());
    return categoryMapper.toCategory(categoryEntity, household);
  }

  @Override
  public List<Category> getAll() {
    return categoryRedisRepository.findAll().stream()
        .map(
            categoryEntity -> {
              final Household household = householdService.get(categoryEntity.getHouseholdId());
              return categoryMapper.toCategory(categoryEntity, household);
            })
        .toList();
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
