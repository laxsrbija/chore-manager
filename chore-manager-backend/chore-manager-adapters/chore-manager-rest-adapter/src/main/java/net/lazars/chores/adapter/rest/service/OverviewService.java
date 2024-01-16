package net.lazars.chores.adapter.rest.service;


import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.OverviewDto;
import net.lazars.chores.adapter.rest.dto.TaskDto;
import net.lazars.chores.adapter.rest.mapper.DtoMapper;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.in.TaskService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OverviewService {

  private static final Comparator<Task> OCCURRENCE_COMPARATOR =
      Comparator.comparingLong(task -> task.getOccurrence().getDaysUntilNextOccurrence());

  private final TaskService taskService;

  public OverviewDto getOverview(final User currentUser) {
    final List<Task> tasks = taskService.getAllInFocus();

    final List<TaskDto> upcoming =
        tasks.stream()
            .filter(task -> taskBelongsToUser(task, currentUser))
            .filter(task -> task.getOccurrence().getDaysUntilNextOccurrence() > 0)
            .sorted(OCCURRENCE_COMPARATOR.thenComparingInt(task -> task.getHistory().size()))
            .map(DtoMapper.INSTANCE::toTaskDto)
            .toList();

    final List<TaskDto> overdue =
        tasks.stream()
            .filter(task -> taskBelongsToUser(task, currentUser))
            .filter(task -> task.getOccurrence().getDaysUntilNextOccurrence() <= 0)
            .sorted(OCCURRENCE_COMPARATOR)
            .map(DtoMapper.INSTANCE::toTaskDto)
            .toList();

    return OverviewDto.builder().upcoming(upcoming).overdue(overdue).build();
  }

  public List<TaskDto> getOtherTasks(final User user) {
    final List<String> tasksInFocus =
        taskService.getAllInFocus().stream().map(Task::getId).toList();
    return taskService.getAll().stream()
        .filter(Task::isEnabled)
        .filter(task -> taskBelongsToUser(task, user))
        .filter(task -> !tasksInFocus.contains(task.getId()))
        .sorted(OCCURRENCE_COMPARATOR)
        .map(DtoMapper.INSTANCE::toTaskDto)
        .toList();
  }

  public List<TaskDto> getDisabledTasks(final User user) {
    return taskService.getAll().stream()
        .filter(task -> !task.isEnabled())
        .filter(task -> taskBelongsToUser(task, user))
        .sorted(Comparator.comparing(Task::getName))
        .map(DtoMapper.INSTANCE::toTaskDto)
        .toList();
  }

  private boolean taskBelongsToUser(final Task task, final User user) {
    return user.getHouseholds().stream()
        .map(Household::getId)
        .toList()
        .contains(task.getItem().getCategory().getHousehold().getId());
  }
}
