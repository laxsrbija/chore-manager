package net.lazars.chores.core.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.core.exception.ChoreManagerException;
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
  public List<User> get(final Collection<String> ids) {
    return userRepository.get(ids);
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

  @Override
  public Optional<User> findByEmail(final String email) {
    final List<User> matchingUsers =
        getAll().stream().filter(user -> user.getEmail().equals(email)).toList();

    if (matchingUsers.size() > 1) {
      throw new ChoreManagerException("More that one user with email '" + email + "'");
    }

    return matchingUsers.isEmpty() ? Optional.empty() : Optional.of(matchingUsers.get(0));
  }
}
