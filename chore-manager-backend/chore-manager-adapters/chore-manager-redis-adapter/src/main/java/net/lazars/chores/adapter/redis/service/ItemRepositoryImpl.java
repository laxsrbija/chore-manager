package net.lazars.chores.adapter.redis.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.redis.document.ItemDocument;
import net.lazars.chores.adapter.redis.mapper.ItemMapper;
import net.lazars.chores.adapter.redis.repository.ItemRedisRepository;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.model.Item;
import net.lazars.chores.core.port.in.CategoryService;
import net.lazars.chores.core.port.out.ItemRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ItemRepositoryImpl extends EntityRepository<Item> implements ItemRepository {

  private final ItemRedisRepository itemRepository;
  private final ItemMapper itemMapper;
  private final CategoryService categoryService;

  @Override
  public Item get(final String id) {
    final ItemDocument itemEntity = itemRepository.findById(id).orElseThrow();
    final Category category = categoryService.get(itemEntity.getCategoryId());
    return itemMapper.toItem(itemEntity, category);
  }

  @Override
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
  protected void saveEntity(final Item entity) {
    final ItemDocument itemEntity = itemMapper.toItemDocument(entity);
    itemRepository.save(itemEntity);
  }

  @Override
  public void delete(final String id) {
    itemRepository.deleteById(id);
  }
}
