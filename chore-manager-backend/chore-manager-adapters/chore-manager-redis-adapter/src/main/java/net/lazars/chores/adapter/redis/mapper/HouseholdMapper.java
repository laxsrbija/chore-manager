package net.lazars.chores.adapter.redis.mapper;

import net.lazars.chores.adapter.db.entity.HouseholdDocument;
import net.lazars.chores.core.model.Household;
import org.mapstruct.Mapper;

@Mapper
public interface HouseholdMapper {

  Household toHousehold(HouseholdDocument householdDocument);

  HouseholdDocument toHouseholdDocument(Household household);
}
