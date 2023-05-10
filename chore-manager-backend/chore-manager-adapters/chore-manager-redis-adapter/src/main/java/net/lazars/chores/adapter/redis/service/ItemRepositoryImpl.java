package net.lazars.chores.adapter.redis.service;

import static net.lazars.chores.adapter.redis.config.RedisCacheConfiguration.ITEM_CACHE;
import static net.lazars.chores.adapter.redis.config.RedisCacheConfiguration.ITEM_GROUP_CACHE;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lazars.chores.adapter.redis.document.ItemDocument;
import net.lazars.chores.adapter.redis.mapper.ItemMapper;
import net.lazars.chores.adapter.redis.repository.ItemRedisRepository;
import net.lazars.chores.adapter.redis.service.CacheEvictionService.CacheType;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.model.Item;
import net.lazars.chores.core.port.in.CategoryService;
import net.lazars.chores.core.port.out.ItemRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
class ItemRepositoryImpl extends EntityRepository<Item> implements ItemRepository {

  private final ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);
  private final ItemRedisRepository itemRepository;
  private final CategoryService categoryService;
  private final CacheEvictionService cacheEvictionService;

  @Override
  @Cacheable(value = ITEM_CACHE, key = "#id")
  public Item get(final String id) {
    final ItemDocument itemEntity = itemRepository.findById(id).orElseThrow();
    final Category category = categoryService.get(itemEntity.getCategoryId());
    return itemMapper.toItem(itemEntity, category);
  }

  @Override
  @Cacheable(value = ITEM_CACHE, key = ITEM_GROUP_CACHE)
  public List<Item> getAll() {
    return itemRepository.findAll().stream()
        .map(
            itemEntity -> {
              final Category category = categoryService.get(itemEntity.getCategoryId());
              return itemMapper.toItem(itemEntity, category);
            })
        .toList();
  }

  @Override
  public void saveEntity(final Item entity) {
    final ItemDocument itemEntity = itemMapper.toItemDocument(entity);
    itemRepository.save(itemEntity);
    cacheEvictionService.evictCache(CacheType.ITEM_CACHE, entity.getId());
  }

  @Override
  @CacheEvict(value = ITEM_CACHE)
  public void delete(final String id) {
    itemRepository.deleteById(id);
    cacheEvictionService.evictCache(CacheType.ITEM_CACHE, id);
  }
}
