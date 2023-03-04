package net.lazars.chores.adapter.rest.controller;

import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.UserDto;
import net.lazars.chores.adapter.rest.mapper.DtoMapper;
import net.lazars.chores.adapter.rest.service.AuthService;
import net.lazars.chores.core.model.User;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountRestController {

  private static final DtoMapper MAPPER = DtoMapper.INSTANCE;

  private final AuthService authService;

  @PostMapping
  public UserDto account(final Authentication authentication) {
    final User user = authService.getCurrentUser(authentication);
    return MAPPER.toUserDto(user);
  }
}
