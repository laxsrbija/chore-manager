package net.lazars.chores.core.service;

import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.port.in.HouseholdService;
import net.lazars.chores.core.port.out.HouseholdRepository;

@RequiredArgsConstructor
public class HouseholdServiceImpl implements HouseholdService {

  private final HouseholdRepository householdRepository;

  @Override
  public Household get(final String id) {
    return householdRepository.get(id);
  }

  @Override
  public List<Household> get(final Collection<String> ids) {
    return householdRepository.get(ids);
  }

  @Override
  public List<Household> getAll() {
    return householdRepository.getAll();
  }

  @Override
  public Household save(final Household object) {
    return householdRepository.save(object);
  }

  @Override
  public void delete(final String id) {
    householdRepository.delete(id);
  }
}
