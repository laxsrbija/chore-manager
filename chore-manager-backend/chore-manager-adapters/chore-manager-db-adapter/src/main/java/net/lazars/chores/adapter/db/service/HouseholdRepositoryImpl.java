package net.lazars.chores.adapter.db.service;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.db.entity.HouseholdDocument;
import net.lazars.chores.adapter.db.mapper.HouseholdMapper;
import net.lazars.chores.adapter.db.repository.HouseholdJsondbRepository;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.port.out.HouseholdRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseholdRepositoryImpl extends EntityRepository<Household>
    implements HouseholdRepository {

  private final HouseholdMapper mapper = Mappers.getMapper(HouseholdMapper.class);
  private final HouseholdJsondbRepository householdJsondbRepository;

  @Override
  protected void saveEntity(final Household household) {
    final HouseholdDocument householdDocument = mapper.toHouseholdDocument(household);
    householdJsondbRepository.save(householdDocument);
  }

  @Override
  public Household get(final String id) {
    final HouseholdDocument householdDocument = householdJsondbRepository.findById(id);
    return mapper.toHousehold(householdDocument);
  }

  @Override
  public List<Household> getAll() {
    return forEach(householdJsondbRepository.findAll(), mapper::toHousehold);
  }

  @Override
  public void delete(final String id) {
    householdJsondbRepository.delete(id);
  }
}
