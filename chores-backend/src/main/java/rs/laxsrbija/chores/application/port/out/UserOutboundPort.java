package rs.laxsrbija.chores.application.port.out;

import java.util.List;
import rs.laxsrbija.chores.domain.User;

public interface UserOutboundPort {

  User getUser(String userId);

  List<User> getAllUsers();
}
