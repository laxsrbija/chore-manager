package net.lazars.chores.adapter.rest.service;

import java.util.Collection;
import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.core.port.in.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    return userService
        .findByEmail(username)
        .map(
            user ->
                User.builder()
                    .username(user.getEmail())
                    .password(user.getEncodedPassword())
                    .build())
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  @Builder
  public static class User implements UserDetails {

    private String username;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of();
    }

    @Override
    public String getPassword() {
      return password;
    }

    @Override
    public String getUsername() {
      return username;
    }

    @Override
    public boolean isAccountNonExpired() {
      return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
      return true;
    }
  }
}
