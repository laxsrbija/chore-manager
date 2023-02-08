package net.lazars.chores.adapter.rest.controller;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.UserDto;
import net.lazars.chores.adapter.rest.mapper.DtoMapper;
import net.lazars.chores.core.port.in.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/service/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountRestController {

  private final UserService userService;

  @PostMapping("login")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void login() {
    // The session was created by accessing this endpoint
  }

  @GetMapping("users")
  public List<UserDto> getUsers() {
    return forEach(userService.getAll(), DtoMapper.INSTANCE::toUserDto);
  }

  @GetMapping("users/{userId}")
  public UserDto getUser(@PathVariable final String userId) {
    return DtoMapper.INSTANCE.toUserDto(userService.get(userId));
  }
}
