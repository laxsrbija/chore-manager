package net.lazars.chores.adapter.redis.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.redis.document.ItemDocument;
import net.lazars.chores.adapter.redis.mapper.ItemMapper;
import net.lazars.chores.adapter.redis.repository.ItemRedisRepository;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.model.Item;
import net.lazars.chores.core.port.in.CategoryService;
import net.lazars.chores.core.port.out.ItemRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ItemRepositoryImpl extends DocumentRepository<Item> implements ItemRepository {

  private final ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);
  private final ItemRedisRepository itemRepository;
  private final CategoryService categoryService;

  @Override
  public Item get(final String id) {
    return convertToItems(List.of(itemRepository.findById(id).orElseThrow())).getFirst();
  }

  @Override
  public List<Item> get(final Collection<String> ids) {
    return convertToItems(itemRepository.findAllById(ids));
  }

  @Override
  public List<Item> getAll() {
    return convertToItems(itemRepository.findAll());
  }

  @Override
  public void saveDocument(final Item document) {
    final ItemDocument itemDocument = itemMapper.toItemDocument(document);
    itemRepository.save(itemDocument);
  }

  @Override
  public void delete(final String id) {
    itemRepository.deleteById(id);
  }

  private List<Item> convertToItems(final List<ItemDocument> items) {
    final Set<String> categoryIds =
        items.stream().map(ItemDocument::getCategoryId).collect(Collectors.toSet());
    final Map<String, Category> categories = collectById(categoryService.get(categoryIds));

    return items.stream()
        .map(item -> itemMapper.toItem(item, categories.get(item.getCategoryId())))
        .toList();
  }
}
