package net.lazars.chores.core.service;

import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.core.model.Item;
import net.lazars.chores.core.port.in.ItemService;
import net.lazars.chores.core.port.out.ItemRepository;

@RequiredArgsConstructor
class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;

  @Override
  public Item get(final String id) {
    return itemRepository.get(id);
  }

  @Override
  public List<Item> get(final Collection<String> ids) {
    return itemRepository.get(ids);
  }

  @Override
  public List<Item> getAll() {
    return itemRepository.getAll();
  }

  @Override
  public Item save(final Item object) {
    return itemRepository.save(object);
  }

  @Override
  public void delete(final String id) {
    itemRepository.delete(id);
  }
}
