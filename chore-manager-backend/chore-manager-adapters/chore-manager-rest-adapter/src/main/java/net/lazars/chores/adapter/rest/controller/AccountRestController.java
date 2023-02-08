package net.lazars.chores.adapter.rest.controller;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.UserDto;
import net.lazars.chores.adapter.rest.mapper.DtoMapper;
import net.lazars.chores.adapter.rest.util.AuthenticationUtils;
import net.lazars.chores.core.port.in.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/service/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountRestController {

  private final UserService userService;

  @PostMapping
  public UserDto account(final Authentication authentication) {
    final String userEmail = AuthenticationUtils.getAuthenticatedUserEmail(authentication);
    return DtoMapper.INSTANCE.toUserDto(userService.findByEmail(userEmail).orElseThrow());
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
