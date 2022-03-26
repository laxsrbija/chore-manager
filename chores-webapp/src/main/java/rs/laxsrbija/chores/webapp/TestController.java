package rs.laxsrbija.chores.webapp;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import rs.laxsrbija.chores.data.entity.UserEntity;
import rs.laxsrbija.chores.data.repository.UserRepository;

@RestController
@RequiredArgsConstructor
public class TestController
{
	private final UserRepository _userRepository;

	@GetMapping
	public List<UserEntity> getUsers()
	{
		return _userRepository.findAll();
	}

	@PostMapping
	public void save(@RequestParam final String id)
	{
		final UserEntity user = UserEntity.builder()
			.id(id)
			.name("ASD")
			.build();
		_userRepository.save(user);
	}

	@DeleteMapping
	public void delete(@RequestParam final String id)
	{
		_userRepository.delete(id);
	}
}
