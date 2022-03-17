package rs.laxsrbija.chores.core.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import rs.laxsrbija.chores.core.mapper.UserMapper;
import rs.laxsrbija.chores.data.entity.UserEntity;
import rs.laxsrbija.chores.data.repository.UserRepository;
import rs.laxsrbija.chores.shared.model.dto.User;

@Service
@RequiredArgsConstructor
public class UserService
{
	private final UserRepository _userRepository;
	private final UserMapper _userMapper;

	public User getUser(final String id)
	{
		final UserEntity userEntity = _userRepository.findById(id);
		return _userMapper.toUser(userEntity);
	}
}
