package net.lazars.chores.adapter.rest.controller;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.UserDto;
import net.lazars.chores.adapter.rest.mapper.DtoMapper;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.CrudOperations;
import net.lazars.chores.core.port.in.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController implements CrudOperations<UserDto> {

  public final UserService userService;

  @Override
  @GetMapping("{id}")
  public UserDto get(@PathVariable final String id) {
    return DtoMapper.INSTANCE.toUserDto(userService.get(id));
  }

  @Override
  @GetMapping
  public List<UserDto> getAll() {
    return forEach(userService.getAll(), DtoMapper.INSTANCE::toUserDto);
  }

  @Override
  @PutMapping
  public UserDto save(@RequestBody final UserDto userDto) {
    final User user = DtoMapper.INSTANCE.toUser(userDto);
    return DtoMapper.INSTANCE.toUserDto(userService.save(user));
  }

  @PostMapping
  public List<UserDto> saveAll(@RequestBody final List<UserDto> users) {
    return users.stream().map(this::save).toList();
  }

  @Override
  @DeleteMapping("{id}")
  public void delete(@PathVariable final String id) {
    userService.delete(id);
  }
}
