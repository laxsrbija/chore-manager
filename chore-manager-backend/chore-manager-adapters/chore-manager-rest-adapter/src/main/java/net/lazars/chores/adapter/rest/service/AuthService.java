package net.lazars.chores.adapter.rest.service;

import lombok.RequiredArgsConstructor;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.in.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserService userService;

  public User getCurrentUser(final Authentication authentication) {
    final String username =
        ((UserAuthenticationService.User) authentication.getPrincipal()).getUsername();
    return userService.findByEmail(username).orElseThrow();
  }

  public String getCurrentUserId(final Authentication authentication) {
    return getCurrentUser(authentication).getId();
  }
}
