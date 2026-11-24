package net.lazars.chores.adapter.sqlite.service;

import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.sqlite.mapper.EntityMapper;
import net.lazars.chores.adapter.sqlite.repository.HouseholdJpaRepository;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.port.out.HouseholdRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class HouseholdRepositoryAdapter implements HouseholdRepository {

  private final HouseholdJpaRepository repository;
  private final EntityMapper mapper;

  @Override
  @Transactional(readOnly = true)
  public Household get(final String id) {
    return mapper.toHousehold(repository.findById(id).orElseThrow());
  }

  @Override
  @Transactional(readOnly = true)
  public List<Household> get(final Collection<String> ids) {
    return repository.findAllById(ids).stream().map(mapper::toHousehold).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Household> getAll() {
    return repository.findAll().stream().map(mapper::toHousehold).toList();
  }

  @Override
  @Transactional
  public Household save(final Household household) {
    return mapper.toHousehold(repository.save(mapper.toHouseholdEntity(household)));
  }

  @Override
  @Transactional
  public void delete(final String id) {
    repository.deleteById(id);
  }
}