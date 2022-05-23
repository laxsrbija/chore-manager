package rs.laxsrbija.chores.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.laxsrbija.chores.adapter.out.persistence.entity.UserEntity;
import rs.laxsrbija.chores.adapter.out.persistence.mapper.UserMapper;
import rs.laxsrbija.chores.adapter.out.persistence.repository.UserRepository;
import rs.laxsrbija.chores.domain.model.dto.User;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository _userRepository;
  private final UserMapper _userMapper;

  public User getUser(final String id) {
    final UserEntity userEntity = _userRepository.findById(id);
    return _userMapper.toUser(userEntity);
  }
}
