package net.lazars.chores.adapter.rest.service;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.OverviewDto;
import net.lazars.chores.adapter.rest.mapper.DtoMapper;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.in.CategoryService;
import net.lazars.chores.core.port.in.ItemService;
import net.lazars.chores.core.port.in.TaskService;
import net.lazars.chores.core.port.in.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OverviewService {

  private static final Comparator<Task> OCCURRENCE_COMPARATOR =
      Comparator.comparingLong(task -> task.getOccurrence().getDaysUntilNextOccurrence());

  private final TaskService taskService;
  private final ItemService itemService;
  private final CategoryService categoryService;
  private final UserService userService;

  // TODO: Move to a separate request
  //  private final BuildProperties buildProperties;

  public OverviewDto getOverview(final User currentUser) {
    final List<Task> tasks =
        taskService.getAll().stream()
            .filter(
                task ->
                    currentUser.getHouseholds().stream()
                        .map(Household::getId)
                        .toList()
                        .contains(task.getItem().getCategory().getHousehold().getId()))
            .toList();

    final List<Task> upcoming =
        tasks.stream()
            .filter(Task::isEnabled)
            .filter(task -> task.getOccurrence().getDaysUntilNextOccurrence() > 0)
            .sorted(OCCURRENCE_COMPARATOR.thenComparingInt(task -> task.getHistory().size()))
            .toList();

    final List<Task> overdue =
        tasks.stream()
            .filter(Task::isEnabled)
            .filter(task -> task.getOccurrence().getDaysUntilNextOccurrence() <= 0)
            .sorted(OCCURRENCE_COMPARATOR)
            .toList();

    final List<Task> disabled =
        tasks.stream()
            .filter(task -> !task.isEnabled())
            .sorted(Comparator.comparing(Task::getName))
            .toList();

    return OverviewDto.builder()
        .upcoming(forEach(upcoming, DtoMapper.INSTANCE::toTaskDto))
        .overdue(forEach(overdue, DtoMapper.INSTANCE::toTaskDto))
        .disabled(forEach(disabled, DtoMapper.INSTANCE::toTaskDto))
        // TODO: Move to a separate endpoint
        //        .taskCount(tasks.size())
        //        .itemCount(itemService.getAll().size())
        //        .categoryCount(categoryService.getAll().size())
        //        .userCount(userService.getAll().size())
        .build();
  }
}
