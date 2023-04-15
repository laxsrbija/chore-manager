package net.lazars.chores.adapter.redis.repository;

import net.lazars.chores.adapter.redis.document.UserDocument;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedisRepository extends ListCrudRepository<UserDocument, String> {}
