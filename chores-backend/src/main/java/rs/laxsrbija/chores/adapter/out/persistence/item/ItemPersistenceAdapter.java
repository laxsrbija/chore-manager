package rs.laxsrbija.chores.adapter.out.persistence.item;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.adapter.out.persistence.entity.ItemEntity;
import rs.laxsrbija.chores.application.port.out.CategoryOutboundPort;
import rs.laxsrbija.chores.application.port.out.ItemOutboundPort;
import rs.laxsrbija.chores.application.util.IdGenerator;
import rs.laxsrbija.chores.domain.Category;
import rs.laxsrbija.chores.domain.Item;

@Component
@RequiredArgsConstructor
class ItemPersistenceAdapter implements ItemOutboundPort {

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
        .map(itemEntity -> {
          final Category category = categoryOutboundPort.get(itemEntity.getCategoryId());
          return itemMapper.toItem(itemEntity, category);
        })
        .collect(Collectors.toList());
  }

  @Override
  public Item save(final Item item) {
    IdGenerator.validateId(item);

    final ItemEntity itemEntity = itemMapper.toItemEntity(item);
    itemRepository.save(itemEntity);

    return get(item.getId());
  }

  @Override
  public void delete(final String id) {
    itemRepository.delete(id);
  }
}
