package net.lazars.chores.adapter.redis.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.redis.document.CategoryDocument;
import net.lazars.chores.adapter.redis.mapper.CategoryMapper;
import net.lazars.chores.adapter.redis.repository.CategoryRedisRepository;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.port.in.HouseholdService;
import net.lazars.chores.core.port.out.CategoryRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CategoryRepositoryImpl extends DocumentRepository<Category> implements CategoryRepository {

  private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
  private final CategoryRedisRepository categoryRedisRepository;
  private final HouseholdService householdService;

  @Override
  public Category get(final String id) {
    return convertToCategories(List.of(categoryRedisRepository.findById(id).orElseThrow()))
        .getFirst();
  }

  @Override
  public List<Category> get(final Collection<String> ids) {
    return convertToCategories(categoryRedisRepository.findAllById(ids));
  }

  @Override
  public List<Category> getAll() {
    return convertToCategories(categoryRedisRepository.findAll());
  }

  @Override
  public void saveDocument(final Category document) {
    final CategoryDocument categoryDocument = categoryMapper.toCategoryDocument(document);
    categoryRedisRepository.save(categoryDocument);
  }

  @Override
  public void delete(final String id) {
    categoryRedisRepository.deleteById(id);
  }

  private List<Category> convertToCategories(final List<CategoryDocument> categories) {
    final Set<String> householdIds =
        categories.stream().map(CategoryDocument::getHouseholdId).collect(Collectors.toSet());
    final Map<String, Household> households = collectById(householdService.get(householdIds));

    return categories.stream()
        .map(
            category ->
                categoryMapper.toCategory(category, households.get(category.getHouseholdId())))
        .toList();
  }
}
