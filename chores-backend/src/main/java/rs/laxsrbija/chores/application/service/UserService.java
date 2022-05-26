package rs.laxsrbija.chores.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.laxsrbija.chores.application.port.out.UserOutboundPort;
import rs.laxsrbija.chores.domain.User;

@Service
@RequiredArgsConstructor
class UserService {

  private final UserOutboundPort userOutboundPort;

  public User getUser(final String userId) {
    return userOutboundPort.getUser(userId);
  }
}
