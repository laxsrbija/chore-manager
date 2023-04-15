package net.lazars.chores.adapter.redis.repository;

import net.lazars.chores.adapter.redis.document.ItemDocument;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRedisRepository extends ListCrudRepository<ItemDocument, String> {}
