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
			.email(userEntity.getEmail())
			.build();
	}
}
