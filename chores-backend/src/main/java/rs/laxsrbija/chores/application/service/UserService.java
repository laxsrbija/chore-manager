package rs.laxsrbija.chores.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.laxsrbija.chores.application.port.in.UserInboundPort;
import rs.laxsrbija.chores.application.port.out.UserOutboundPort;
import rs.laxsrbija.chores.domain.User;

@Service
@RequiredArgsConstructor
class UserService implements UserInboundPort {

  private final UserOutboundPort userOutboundPort;

  @Override
  public User get(final String userId) {
    return userOutboundPort.get(userId);
  }

  @Override
  public List<User> getAll() {
    return userOutboundPort.getAll();
  }

  @Override
  public User save(final User object) {
    return userOutboundPort.save(object);
  }

  @Override
  public void delete(final String id) {
    userOutboundPort.delete(id);
  }
}
