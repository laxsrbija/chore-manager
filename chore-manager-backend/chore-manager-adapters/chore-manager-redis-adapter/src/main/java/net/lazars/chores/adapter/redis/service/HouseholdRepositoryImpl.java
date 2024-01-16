package net.lazars.chores.adapter.redis.service;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.redis.document.HouseholdDocument;
import net.lazars.chores.adapter.redis.mapper.HouseholdMapper;
import net.lazars.chores.adapter.redis.repository.HouseholdRedisRepository;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.port.out.HouseholdRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseholdRepositoryImpl extends DocumentRepository<Household>
    implements HouseholdRepository {

  private final HouseholdMapper mapper = Mappers.getMapper(HouseholdMapper.class);
  private final HouseholdRedisRepository householdRedisRepository;

  @Override
  public void saveDocument(final Household household) {
    final HouseholdDocument householdDocument = mapper.toHouseholdDocument(household);
    householdRedisRepository.save(householdDocument);
  }

  @Override
  public Household get(final String id) {
    final HouseholdDocument householdDocument = householdRedisRepository.findById(id).orElseThrow();
    return mapper.toHousehold(householdDocument);
  }

  @Override
  public List<Household> get(final Collection<String> ids) {
    // This is fine as there are not many households
    return getAll().stream().filter(household -> ids.contains(household.getId())).toList();
  }

  @Override
  public List<Household> getAll() {
    return forEach(householdRedisRepository.findAll(), mapper::toHousehold);
  }

  @Override
  public void delete(final String id) {
    householdRedisRepository.deleteById(id);
  }
}
