package net.lazars.chores.core.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.in.UserService;
import net.lazars.chores.core.port.out.UserRepository;

@RequiredArgsConstructor
class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User get(final String userId) {
    return userRepository.get(userId);
  }

  @Override
  public List<User> getAll() {
    return userRepository.getAll();
  }

  @Override
  public User save(final User object) {
    return userRepository.save(object);
  }

  @Override
  public void delete(final String id) {
    userRepository.delete(id);
  }
}
