package net.lazars.chores.adapter.db.service;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.db.entity.UserDocument;
import net.lazars.chores.adapter.db.mapper.UserMapper;
import net.lazars.chores.adapter.db.repository.UserJsondbRepository;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.out.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserRepositoryImpl extends EntityRepository<User> implements UserRepository {

  private final UserJsondbRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public User get(final String id) {
    final UserDocument userEntity = userRepository.findById(id);
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
    userRepository.delete(id);
  }
}
