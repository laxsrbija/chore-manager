package net.lazars.chores.adapter.redis.mapper;

import net.lazars.chores.adapter.redis.document.UserDocument;
import java.util.List;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public User toUser(final UserDocument userEntity, final List<Household> households) {
    return User.builder()
        .id(userEntity.getId())
        .name(userEntity.getName())
        .email(userEntity.getEmail())
        .encodedPassword(userEntity.getEncodedPassword())
        .image(userEntity.getImage())
        .permissions(userEntity.getPermissions() != null ? userEntity.getPermissions() : List.of())
        .households(households)
        .build();
  }

  public UserDocument toUserDocument(final User user) {
    return UserDocument.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .encodedPassword(user.getEncodedPassword())
        .image(user.getImage())
        .permissions(user.getPermissions())
        .householdIds(
            user.getHouseholds() != null
                ? user.getHouseholds().stream().map(Household::getId).toList()
                : List.of())
        .build();
  }
}