package net.lazars.chores.adapter.rest.controller.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.HouseholdDto;
import net.lazars.chores.adapter.rest.dto.UserDto;
import net.lazars.chores.adapter.rest.mapper.DtoMapper;
import net.lazars.chores.adapter.rest.service.AuthService;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.in.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/service/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountRestController {

  private static final DtoMapper MAPPER = DtoMapper.INSTANCE;

  private final AuthService authService;
  private final UserService userService;

  @PostMapping
  public UserDto account(final Authentication authentication) {
    final User user = authService.getCurrentUser(authentication);
    return MAPPER.toUserDto(user);
  }

  @GetMapping("users")
  public Map<String, List<UserDto>> getUsersPerHousehold(final Authentication authentication) {
    final User user = authService.getCurrentUser(authentication);
    final List<String> householdIdsOfCurrentUser =
        user.getHouseholds().stream().map(Household::getId).toList();

    final List<UserDto> visibleUsers =
        userService.getAll().stream()
            .filter(
                otherUser ->
                    otherUser.getHouseholds().stream()
                        .map(Household::getId)
                        .anyMatch(householdIdsOfCurrentUser::contains))
            .map(MAPPER::toUserDto)
            .toList();

    final Map<String, List<UserDto>> usersPerHousehold = new HashMap<>();
    for (final UserDto userDto : visibleUsers) {
      for (final HouseholdDto household : userDto.getHouseholds()) {
        usersPerHousehold.computeIfAbsent(household.getId(), k -> new ArrayList<>()).add(userDto);
      }
    }
    
    return usersPerHousehold;
  }
}
