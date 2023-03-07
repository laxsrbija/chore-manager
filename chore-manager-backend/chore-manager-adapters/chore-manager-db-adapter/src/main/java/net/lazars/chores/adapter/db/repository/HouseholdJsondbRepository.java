package net.lazars.chores.adapter.db.repository;

import io.jsondb.JsonDBTemplate;
import net.lazars.chores.adapter.db.entity.HouseholdDocument;
import org.springframework.stereotype.Repository;

@Repository
public class HouseholdJsondbRepository extends CrudRepository<HouseholdDocument> {

  public HouseholdJsondbRepository(final JsonDBTemplate jsonDBTemplate) {
    super(jsonDBTemplate, HouseholdDocument.class);
  }
}
