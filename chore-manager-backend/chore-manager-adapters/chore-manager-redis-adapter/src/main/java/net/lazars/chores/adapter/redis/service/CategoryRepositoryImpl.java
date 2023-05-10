package net.lazars.chores.adapter.redis.service;


import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.redis.document.CategoryDocument;
import net.lazars.chores.adapter.redis.mapper.CategoryMapper;
import net.lazars.chores.adapter.redis.repository.CategoryRedisRepository;
import net.lazars.chores.adapter.redis.service.CacheEvictionService.CacheType;
import net.lazars.chores.core.model.Category;
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
  private final CacheEvictionService cacheEvictionService;

  @Override
  public Category get(final String id) {
    final CategoryDocument categoryEntity = categoryRedisRepository.findById(id).orElseThrow();
    return categoryMapper.toCategory(
        categoryEntity,
        Optional.ofNullable(categoryEntity.getHouseholdId())
            .map(householdService::get)
            .orElse(null));
  }

  @Override
  public List<Category> getAll() {
    return categoryRedisRepository.findAll().stream()
        .map(
            categoryEntity ->
                categoryMapper.toCategory(
                    categoryEntity,
                    Optional.ofNullable(categoryEntity.getHouseholdId())
                        .map(householdService::get)
                        .orElse(null)))
        .toList();
  }

  @Override
  public void saveEntity(final Category entity) {
    final CategoryDocument categoryEntity = categoryMapper.toCategoryDocument(entity);
    categoryRedisRepository.save(categoryEntity);
    cacheEvictionService.evictCache(CacheType.ITEM_CACHE);
  }

  @Override
  public void delete(final String id) {
    categoryRedisRepository.deleteById(id);
    cacheEvictionService.evictCache(CacheType.ITEM_CACHE);
  }
}
