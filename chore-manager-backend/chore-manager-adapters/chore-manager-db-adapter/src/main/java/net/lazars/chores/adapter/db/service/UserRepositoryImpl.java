package net.lazars.chores.adapter.db.service;


import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.db.entity.UserDocument;
import net.lazars.chores.adapter.db.mapper.UserMapper;
import net.lazars.chores.adapter.db.repository.UserJsondbRepository;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.in.HouseholdService;
import net.lazars.chores.core.port.out.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserRepositoryImpl extends EntityRepository<User> implements UserRepository {

  private final UserJsondbRepository userRepository;
  private final UserMapper userMapper;
  private final HouseholdService householdService;

  @Override
  public User get(final String id) {
    final UserDocument userEntity = userRepository.findById(id);
    final List<Household> households =
        userEntity.getHouseholdIds().stream().map(householdService::get).toList();

    return userMapper.toUser(userEntity, households);
  }

  @Override
  public List<User> getAll() {
    return userRepository.findAll().stream()
        .map(
            userDocument -> {
              final List<Household> households =
                  userDocument.getHouseholdIds().stream().map(householdService::get).toList();
              return userMapper.toUser(userDocument, households);
            })
        .toList();
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
