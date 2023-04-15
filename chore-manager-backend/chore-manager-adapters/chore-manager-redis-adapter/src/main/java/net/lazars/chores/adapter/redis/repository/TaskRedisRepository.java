package net.lazars.chores.adapter.redis.repository;

import net.lazars.chores.adapter.redis.document.TaskDocument;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRedisRepository extends ListCrudRepository<TaskDocument, String> {}
