package net.lazars.chores.core.port.in;

import java.util.Optional;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.CrudOperations;

public interface UserService extends CrudOperations<User> {

  Optional<User> findByEmail(String email);
}
