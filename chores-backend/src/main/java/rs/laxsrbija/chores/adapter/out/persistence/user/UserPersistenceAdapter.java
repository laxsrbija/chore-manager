package rs.laxsrbija.chores.adapter.out.persistence.user;

import static rs.laxsrbija.chores.common.Commons.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.adapter.out.persistence.PersistenceAdapter;
import rs.laxsrbija.chores.adapter.out.persistence.entity.UserEntity;
import rs.laxsrbija.chores.application.port.out.UserOutboundPort;
import rs.laxsrbija.chores.domain.User;

@Component
@RequiredArgsConstructor
class UserPersistenceAdapter extends PersistenceAdapter<User> implements UserOutboundPort {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public User get(final String id) {
    final UserEntity userEntity = userRepository.findById(id);
    return userMapper.toUser(userEntity);
  }

  @Override
  public List<User> getAll() {
    return forEach(userRepository.findAll(), userMapper::toUser);
  }

  @Override
  protected void saveEntity(final User entity) {
    final UserEntity userEntity = userMapper.toUserEntity(entity);
    userRepository.save(userEntity);
  }

  @Override
  public void delete(final String id) {
    userRepository.delete(id);
  }
}
