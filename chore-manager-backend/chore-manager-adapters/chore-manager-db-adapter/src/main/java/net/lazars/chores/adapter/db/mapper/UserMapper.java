package net.lazars.chores.adapter.db.mapper;

import net.lazars.chores.adapter.db.entity.UserDocument;
import net.lazars.chores.core.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public User toUser(final UserDocument userEntity) {
    return User.builder()
        .id(userEntity.getId())
        .name(userEntity.getName())
        .email(userEntity.getEmail())
        .image(userEntity.getImage())
        .build();
  }

  public UserDocument toUserDocument(final User user) {
    return UserDocument.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .image(user.getImage())
        .build();
  }
}
