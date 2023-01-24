package net.lazars.chores.adapter.db.mapper;

import net.lazars.chores.adapter.db.entity.UserEntity;
import net.lazars.chores.core.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public User toUser(final UserEntity userEntity) {
    return User.builder()
        .id(userEntity.getId())
        .name(userEntity.getName())
        .email(userEntity.getEmail())
        .image(userEntity.getImage())
        .build();
  }

  public UserEntity toUserEntity(final User user) {
    return UserEntity.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .image(user.getImage())
        .build();
  }
}
