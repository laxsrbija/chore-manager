package rs.laxsrbija.chores.adapter.out.persistence.item;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.adapter.out.persistence.PersistenceAdapter;
import rs.laxsrbija.chores.adapter.out.persistence.entity.ItemEntity;
import rs.laxsrbija.chores.application.port.out.CategoryOutboundPort;
import rs.laxsrbija.chores.application.port.out.ItemOutboundPort;
import rs.laxsrbija.chores.domain.Category;
import rs.laxsrbija.chores.domain.Item;

@Component
@RequiredArgsConstructor
class ItemPersistenceAdapter extends PersistenceAdapter<Item> implements ItemOutboundPort {

  private final ItemRepository itemRepository;
  private final ItemMapper itemMapper;
  private final CategoryOutboundPort categoryOutboundPort;

  @Override
  public Item get(final String id) {
    final ItemEntity itemEntity = itemRepository.findById(id);
    final Category category = categoryOutboundPort.get(itemEntity.getCategoryId());
    return itemMapper.toItem(itemEntity, category);
  }

  @Override
  public List<Item> getAll() {
    return itemRepository.findAll().stream()
        .map(
            itemEntity -> {
              final Category category = categoryOutboundPort.get(itemEntity.getCategoryId());
              return itemMapper.toItem(itemEntity, category);
            })
        .toList();
  }

  @Override
  protected void saveEntity(final Item entity) {
    final ItemEntity itemEntity = itemMapper.toItemEntity(entity);
    itemRepository.save(itemEntity);
  }

  @Override
  public void delete(final String id) {
    itemRepository.delete(id);
  }
}
