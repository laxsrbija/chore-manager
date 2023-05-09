package net.lazars.chores.adapter.redis.service;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.redis.document.HouseholdDocument;
import net.lazars.chores.adapter.redis.mapper.HouseholdMapper;
import net.lazars.chores.adapter.redis.repository.HouseholdRedisRepository;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.port.out.HouseholdRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseholdRepositoryImpl extends EntityRepository<Household>
    implements HouseholdRepository {

  private final HouseholdMapper mapper = Mappers.getMapper(HouseholdMapper.class);
  private final HouseholdRedisRepository householdRedisRepository;

  @Override
  @CacheEvict(value = "households", allEntries = true)
  public void saveEntity(final Household household) {
    final HouseholdDocument householdDocument = mapper.toHouseholdDocument(household);
    householdRedisRepository.save(householdDocument);
  }

  @Override
  @Cacheable(value = "households")
  public Household get(final String id) {
    final HouseholdDocument householdDocument = householdRedisRepository.findById(id).orElseThrow();
    return mapper.toHousehold(householdDocument);
  }

  @Override
  @Cacheable(value = "households")
  public List<Household> getAll() {
    return forEach(householdRedisRepository.findAll(), mapper::toHousehold);
  }

  @Override
  @CacheEvict(value = "households", allEntries = true)
  public void delete(final String id) {
    householdRedisRepository.deleteById(id);
  }
}
