package net.lazars.chores.adapter.rest.controller.service;

import static net.lazars.chores.adapter.rest.util.AuthenticationUtils.isUserInHousehold;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.CategoryDto;
import net.lazars.chores.adapter.rest.dto.HouseholdDto;
import net.lazars.chores.adapter.rest.dto.ItemDto;
import net.lazars.chores.adapter.rest.dto.TaskDto;
import net.lazars.chores.adapter.rest.dto.UserDto;
import net.lazars.chores.adapter.rest.mapper.DtoMapper;
import net.lazars.chores.adapter.rest.service.AuthService;
import net.lazars.chores.core.model.BaseModel;
import net.lazars.chores.core.model.Category;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.model.Item;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.in.CategoryService;
import net.lazars.chores.core.port.in.HouseholdService;
import net.lazars.chores.core.port.in.ItemService;
import net.lazars.chores.core.port.in.TaskService;
import net.lazars.chores.core.port.in.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  private final TaskService taskService;

  @GetMapping("households")
  public List<HouseholdDto> getHouseholds(final Authentication authentication) {
    final User user = authService.getCurrentUser(authentication);
    return householdService.getAll().stream()
        .filter(household -> isUserInHousehold(user, household))
        .sorted(Comparator.comparing(BaseModel::getName))
        .map(mapper::toHouseholdDto)
        .toList();
  }

  @PostMapping("households")
  @PreAuthorize("hasAuthority('EDIT')")
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
        .sorted(
            Comparator.<Category, String>comparing(category -> category.getHousehold().getName())
                .thenComparing(BaseModel::getName))
        .map(mapper::toCategoryDto)
        .toList();
  }

  @PostMapping("categories")
  @PreAuthorize("hasAuthority('EDIT')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void saveCategory(
      @RequestBody final CategoryDto categoryDto, final Authentication authentication) {
    final User user = authService.getCurrentUser(authentication);
    final Category category = mapper.toCategory(categoryDto);
    final Household household = householdService.get(category.getHousehold().getId());

    if (!isUserInHousehold(user, household)) {
      throw new IllegalArgumentException("Category cannot be created or updated by current user");
    }

    category.setHousehold(household);
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
        .sorted(
            Comparator.<Item, String>comparing(item -> item.getCategory().getName())
                .thenComparing(BaseModel::getName))
        .map(mapper::toItemDto)
        .toList();
  }

  @PostMapping("items")
  @PreAuthorize("hasAuthority('EDIT')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void saveItem(@RequestBody final ItemDto itemDto, final Authentication authentication) {
    final User user = authService.getCurrentUser(authentication);
    final Item item = mapper.toItem(itemDto);
    final Category category = categoryService.get(item.getCategory().getId());

    if (!isUserInHousehold(user, category.getHousehold())) {
      throw new IllegalArgumentException("Category cannot be created or updated by current user");
    }

    item.setCategory(category);
    itemService.save(item);
  }

  @GetMapping("tasks")
  public List<TaskDto> getTasks(
      @RequestParam(required = false) final String itemId, final Authentication authentication) {
    final User user = authService.getCurrentUser(authentication);
    return taskService.getAll().stream()
        .filter(task -> isUserInHousehold(user, task.getItem().getCategory().getHousehold()))
        .filter(task -> itemId == null || task.getItem().getId().equals(itemId))
        .sorted(
            Comparator.<Task, String>comparing(task -> task.getItem().getName())
                .thenComparing(BaseModel::getName))
        .map(mapper::toTaskDto)
        .toList();
  }

  @PatchMapping("tasks/complete/{taskId}")
  public TaskDto completeTask(
      @PathVariable final String taskId,
      @RequestParam(required = false) final String userId,
      @DateTimeFormat(iso = ISO.DATE) @RequestParam(required = false) final LocalDate dateCompleted,
      final Authentication authentication) {
    final String user = userId == null ? authService.getCurrentUserId(authentication) : userId;
    return DtoMapper.INSTANCE.toTaskDto(taskService.markComplete(taskId, user, dateCompleted));
  }

  @PatchMapping("tasks/defer/{taskId}")
  public TaskDto deferTask(
      @PathVariable final String taskId,
      @DateTimeFormat(iso = ISO.DATE) @RequestParam(required = false) final LocalDate deferDate,
      final Authentication authentication) {
    final User user = authService.getCurrentUser(authentication);
    return DtoMapper.INSTANCE.toTaskDto(taskService.defer(taskId, user, deferDate));
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
