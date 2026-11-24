package net.lazars.chores.adapter.sqlite.service;

import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.sqlite.mapper.EntityMapper;
import net.lazars.chores.adapter.sqlite.repository.UserJpaRepository;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.out.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

  private final UserJpaRepository repository;
  private final EntityMapper mapper;

  @Override
  @Transactional(readOnly = true)
  public User get(final String id) {
    return mapper.toUser(repository.findById(id).orElseThrow());
  }

  @Override
  @Transactional(readOnly = true)
  public List<User> get(final Collection<String> ids) {
    return repository.findAllById(ids).stream().map(mapper::toUser).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<User> getAll() {
    return repository.findAll().stream().map(mapper::toUser).toList();
  }

  @Override
  @Transactional
  public User save(final User user) {
    return mapper.toUser(repository.save(mapper.toUserEntity(user)));
  }

  @Override
  @Transactional
  public void delete(final String id) {
    repository.deleteById(id);
  }
}