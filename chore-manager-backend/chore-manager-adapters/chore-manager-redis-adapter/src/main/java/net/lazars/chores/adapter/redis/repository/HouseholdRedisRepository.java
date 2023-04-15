package net.lazars.chores.adapter.redis.repository;

import net.lazars.chores.adapter.redis.document.HouseholdDocument;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseholdRedisRepository extends ListCrudRepository<HouseholdDocument, String> {}
