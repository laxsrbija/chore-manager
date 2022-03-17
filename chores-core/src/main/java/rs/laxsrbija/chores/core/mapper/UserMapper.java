package rs.laxsrbija.chores.core.mapper;

import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.data.entity.UserEntity;
import rs.laxsrbija.chores.shared.model.dto.User;

@Component
public class UserMapper
{
	public User toUser(final UserEntity userEntity)
	{
		return User.builder()
			.id(userEntity.getId())
			.name(userEntity.getName())
			.image(userEntity.getImage())
			.email(userEntity.getEmail())
			.build();
	}

	public UserEntity toUserEntity(final User user)
	{
		return UserEntity.builder()
			.id(user.getId())
			.name(user.getName())
			.image(user.getImage())
			.email(user.getEmail())
			.build();
	}
}
