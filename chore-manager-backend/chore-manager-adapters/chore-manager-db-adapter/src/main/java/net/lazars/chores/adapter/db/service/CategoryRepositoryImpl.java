package net.lazars.chores.adapter.db.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.db.entity.CategoryDocument;
import net.lazars.chores.adapter.db.mapper.CategoryMapper;
import net.lazars.chores.adapter.db.repository.CategoryJsondbRepository;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.port.in.HouseholdService;
import net.lazars.chores.core.port.out.CategoryRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CategoryRepositoryImpl extends EntityRepository<Category> implements CategoryRepository {

  private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
  private final HouseholdService householdService;
  private final CategoryJsondbRepository categoryJsondbRepository;

  @Override
  public Category get(final String id) {
    final CategoryDocument categoryEntity = categoryJsondbRepository.findById(id);
    final Household household = householdService.get(categoryEntity.getHouseholdId());
    return categoryMapper.toCategory(categoryEntity, household);
  }

  @Override
  public List<Category> getAll() {
    return categoryJsondbRepository.findAll().stream()
        .map(
            categoryEntity -> {
              final Household household = householdService.get(categoryEntity.getHouseholdId());
              return categoryMapper.toCategory(categoryEntity, household);
            })
        .toList();
  }

  @Override
  protected void saveEntity(final Category entity) {
    final CategoryDocument categoryEntity = categoryMapper.toCategoryDocument(entity);
    categoryJsondbRepository.save(categoryEntity);
  }

  @Override
  public void delete(final String id) {
    categoryJsondbRepository.delete(id);
  }
}
