package net.lazars.chores.adapter.redis.repository;

import net.lazars.chores.adapter.redis.document.CategoryDocument;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRedisRepository extends ListCrudRepository<CategoryDocument, String> {}
