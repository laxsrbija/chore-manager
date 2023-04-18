package net.lazars.chores.adapter.rest.controller.service;

import static net.lazars.chores.adapter.rest.util.AuthenticationUtils.isUserInHousehold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.CategoryDto;
import net.lazars.chores.adapter.rest.dto.HouseholdDto;
import net.lazars.chores.adapter.rest.dto.ItemDto;
import net.lazars.chores.adapter.rest.dto.UserDto;
import net.lazars.chores.adapter.rest.mapper.DtoMapper;
import net.lazars.chores.adapter.rest.service.AuthService;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.model.Item;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.in.CategoryService;
import net.lazars.chores.core.port.in.HouseholdService;
import net.lazars.chores.core.port.in.ItemService;
import net.lazars.chores.core.port.in.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/service/management", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManagementRestController {

  private final DtoMapper mapper = DtoMapper.INSTANCE;

  private final AuthService authService;
  private final HouseholdService householdService;
  private final CategoryService categoryService;
  private final UserService userService;
  private final ItemService itemService;

  @GetMapping("households")
  public List<HouseholdDto> getHouseholds(final Authentication authentication) {
    final User user = authService.getCurrentUser(authentication);
    return householdService.getAll().stream()
        .filter(household -> isUserInHousehold(user, household))
        .map(mapper::toHouseholdDto)
        .toList();
  }

  @PostMapping("households")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void saveHousehold(
      @RequestBody final HouseholdDto householdDto, final Authentication authentication) {
    final User user = authService.getCurrentUser(authentication);
    final Household household = mapper.toHousehold(householdDto);

    if (household.getId() == null || !isUserInHousehold(user, household)) {
      throw new IllegalArgumentException("Household cannot be created or updated by current user");
    }

    householdService.save(household);
  }

  @GetMapping("categories")
  public List<CategoryDto> getCategories(
      @RequestParam(required = false) final String householdId,
      final Authentication authentication) {
    final User user = authService.getCurrentUser(authentication);
    return categoryService.getAll().stream()
        .filter(category -> isUserInHousehold(user, category.getHousehold()))
        .filter(
            category -> householdId == null || category.getHousehold().getId().equals(householdId))
        .map(mapper::toCategoryDto)
        .toList();
  }

  @PostMapping("categories")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void saveCategory(
      @RequestBody final CategoryDto categoryDto, final Authentication authentication) {
    final User user = authService.getCurrentUser(authentication);
    final Category category = mapper.toCategory(categoryDto);

    if (!isUserInHousehold(user, category.getHousehold())) {
      throw new IllegalArgumentException("Category cannot be created or updated by current user");
    }

    categoryService.save(category);
  }

  @GetMapping("items")
  public List<ItemDto> getItems(
      @RequestParam(required = false) final String categoryId,
      final Authentication authentication) {
    final User user = authService.getCurrentUser(authentication);
    return itemService.getAll().stream()
        .filter(item -> isUserInHousehold(user, item.getCategory().getHousehold()))
        .filter(item -> categoryId == null || item.getCategory().getId().equals(categoryId))
        .map(mapper::toItemDto)
        .toList();
  }

  @PostMapping("items")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void saveItem(@RequestBody final ItemDto itemDto, final Authentication authentication) {
    final User user = authService.getCurrentUser(authentication);
    final Item item = mapper.toItem(itemDto);

    if (!isUserInHousehold(user, item.getCategory().getHousehold())) {
      throw new IllegalArgumentException("Category cannot be created or updated by current user");
    }

    itemService.save(item);
  }

  @GetMapping("users")
  public Map<String, List<UserDto>> getUsersPerHousehold(final Authentication authentication) {
    final User user = authService.getCurrentUser(authentication);

    final List<UserDto> visibleUsers =
        userService.getAll().stream()
            .filter(
                otherUser ->
                    otherUser.getHouseholds().stream()
                        .anyMatch(household -> isUserInHousehold(user, household)))
            .map(mapper::toUserDto)
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
