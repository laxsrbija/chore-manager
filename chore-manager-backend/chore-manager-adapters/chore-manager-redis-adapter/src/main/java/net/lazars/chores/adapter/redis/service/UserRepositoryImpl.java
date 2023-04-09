package net.lazars.chores.adapter.redis.service;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.redis.document.UserDocument;
import net.lazars.chores.adapter.redis.mapper.UserMapper;
import net.lazars.chores.adapter.redis.repository.UserRedisRepository;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.out.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserRepositoryImpl extends EntityRepository<User> implements UserRepository {

  private final UserRedisRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public User get(final String id) {
    final UserDocument userEntity = userRepository.findById(id).orElseThrow();
    return userMapper.toUser(userEntity);
  }

  @Override
  public List<User> getAll() {
    return forEach(userRepository.findAll(), userMapper::toUser);
  }

  @Override
  protected void saveEntity(final User entity) {
    final UserDocument userEntity = userMapper.toUserDocument(entity);
    userRepository.save(userEntity);
  }

  @Override
  public void delete(final String id) {
    userRepository.deleteById(id);
  }
}
