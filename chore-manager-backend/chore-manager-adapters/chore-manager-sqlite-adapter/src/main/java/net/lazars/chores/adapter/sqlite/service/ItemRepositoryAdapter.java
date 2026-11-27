package net.lazars.chores.adapter.sqlite.service;

import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.sqlite.mapper.EntityMapper;
import net.lazars.chores.adapter.sqlite.repository.ItemJpaRepository;
import net.lazars.chores.core.model.Item;
import net.lazars.chores.core.port.ItemRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryAdapter implements ItemRepository {

  private final ItemJpaRepository repository;
  private final EntityMapper mapper;

  @Override
  @Transactional(readOnly = true)
  public Item get(final String id) {
    return mapper.toItem(repository.findById(id).orElseThrow());
  }

  @Override
  @Transactional(readOnly = true)
  public List<Item> get(final Collection<String> ids) {
    return repository.findAllById(ids).stream().map(mapper::toItem).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Item> getAll() {
    return repository.findAll().stream().map(mapper::toItem).toList();
  }

  @Override
  @Transactional
  public Item save(final Item item) {
    return mapper.toItem(repository.save(mapper.toItemEntity(item)));
  }

  @Override
  @Transactional
  public void delete(final String id) {
    repository.deleteById(id);
  }
}
