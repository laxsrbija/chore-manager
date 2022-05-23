package rs.laxsrbija.chores.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.adapter.out.persistence.entity.UserEntity;
import rs.laxsrbija.chores.domain.model.dto.User;

@Component
public class UserMapper {

  public User toUser(final UserEntity userEntity) {
    return User.builder()
        .id(userEntity.getId())
        .name(userEntity.getName())
        .email(userEntity.getEmail())
        .build();
  }
}
