package rs.laxsrbija.chores.adapter.out.persistence.user;

import static rs.laxsrbija.chores.common.Commons.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.adapter.out.persistence.entity.UserEntity;
import rs.laxsrbija.chores.application.port.out.UserOutboundPort;
import rs.laxsrbija.chores.domain.User;

@Component
@RequiredArgsConstructor
class UserPersistenceAdapter implements UserOutboundPort {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public User getUser(final String userId) {
    final UserEntity userEntity = userRepository.findById(userId);
    return userMapper.toUser(userEntity);
  }

  @Override
  public List<User> getAllUsers() {
    return forEach(userRepository.findAll(), userMapper::toUser);
  }
}
