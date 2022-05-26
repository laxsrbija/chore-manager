package rs.laxsrbija.chores.adapter.out.persistence.user;

import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.adapter.out.persistence.entity.UserEntity;
import rs.laxsrbija.chores.domain.User;

@Component
class UserMapper {

  public User toUser(final UserEntity userEntity) {
    return User.builder()
        .id(userEntity.getId())
        .name(userEntity.getName())
        .email(userEntity.getEmail())
        .image(userEntity.getImage())
        .build();
  }
}
