package net.lazars.chores.adapter.redis.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.redis.document.UserDocument;
import net.lazars.chores.adapter.redis.mapper.UserMapper;
import net.lazars.chores.adapter.redis.repository.UserRedisRepository;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.in.HouseholdService;
import net.lazars.chores.core.port.out.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserRepositoryImpl extends DocumentRepository<User> implements UserRepository {

  private final UserRedisRepository userRepository;
  private final UserMapper userMapper;
  private final HouseholdService householdService;

  @Override
  public User get(final String id) {
    final UserDocument userDocument = userRepository.findById(id).orElseThrow();
    final List<Household> households =
        Optional.ofNullable(userDocument.getHouseholdIds())
            .map(householdIds -> householdIds.stream().map(householdService::get).toList())
            .orElse(List.of());

    return userMapper.toUser(userDocument, households);
  }

  @Override
  public List<User> get(final Collection<String> ids) {
    // This is fine as there are not many users
    return getAll().stream().filter(user -> ids.contains(user.getId())).toList();
  }

  @Override
  public List<User> getAll() {
    return userRepository.findAll().stream()
        .map(
            userDocument ->
                userMapper.toUser(
                    userDocument,
                    Optional.ofNullable(userDocument.getHouseholdIds())
                        .map(
                            householdIds ->
                                householdIds.stream().map(householdService::get).toList())
                        .orElse(List.of())))
        .toList();
  }

  @Override
  public void saveDocument(final User document) {
    final UserDocument userDocument = userMapper.toUserDocument(document);
    userRepository.save(userDocument);
  }

  @Override
  public void delete(final String id) {
    userRepository.deleteById(id);
  }
}
