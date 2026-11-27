package net.lazars.chores.adapter.sqlite.service;

import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.sqlite.mapper.EntityMapper;
import net.lazars.chores.adapter.sqlite.repository.CategoryJpaRepository;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.port.CategoryRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryAdapter implements CategoryRepository {

  private final CategoryJpaRepository repository;
  private final EntityMapper mapper;

  @Override
  @Transactional(readOnly = true)
  public Category get(final String id) {
    return mapper.toCategory(repository.findById(id).orElseThrow());
  }

  @Override
  @Transactional(readOnly = true)
  public List<Category> get(final Collection<String> ids) {
    return repository.findAllById(ids).stream().map(mapper::toCategory).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Category> getAll() {
    return repository.findAll().stream().map(mapper::toCategory).toList();
  }

  @Override
  @Transactional
  public Category save(final Category category) {
    return mapper.toCategory(repository.save(mapper.toCategoryEntity(category)));
  }

  @Override
  @Transactional
  public void delete(final String id) {
    repository.deleteById(id);
  }
}
