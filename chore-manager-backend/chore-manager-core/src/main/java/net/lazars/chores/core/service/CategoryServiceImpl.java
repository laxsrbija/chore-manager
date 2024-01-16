package net.lazars.chores.core.service;

import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.port.in.CategoryService;
import net.lazars.chores.core.port.out.CategoryRepository;

@RequiredArgsConstructor
class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public Category get(final String id) {
    return categoryRepository.get(id);
  }

  @Override
  public List<Category> get(final Collection<String> ids) {
    return categoryRepository.get(ids);
  }

  @Override
  public List<Category> getAll() {
    return categoryRepository.getAll();
  }

  @Override
  public Category save(final Category object) {
    return categoryRepository.save(object);
  }

  @Override
  public void delete(final String id) {
    categoryRepository.delete(id);
  }
}
