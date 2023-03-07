package net.lazars.chores.adapter.db.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.db.entity.ItemDocument;
import net.lazars.chores.adapter.db.mapper.ItemMapper;
import net.lazars.chores.adapter.db.repository.ItemJsondbRepository;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.model.Item;
import net.lazars.chores.core.port.in.CategoryService;
import net.lazars.chores.core.port.out.ItemRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ItemRepositoryImpl extends EntityRepository<Item> implements ItemRepository {

  private final ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);
  private final ItemJsondbRepository itemRepository;
  private final CategoryService categoryService;

  @Override
  public Item get(final String id) {
    final ItemDocument itemEntity = itemRepository.findById(id);
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
    itemRepository.delete(id);
  }
}
