package rs.laxsrbija.chores.application.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.laxsrbija.chores.domain.Overview;
import rs.laxsrbija.chores.domain.Task;

@Service
@RequiredArgsConstructor
class OverviewService {

  public static final int MAX_OVERVIEW_TASK_SIZE = 10;

  private final TaskService taskService;

  public Overview getOverview() {
    final List<Task> tasks = taskService.getAll();

    final List<Task> upcoming = tasks.stream()
        .filter(task -> task.getOccurrence().getDaysUntilNextOccurrence() >= 0)
        .sorted(Comparator.comparingLong(task -> task.getOccurrence().getDaysUntilNextOccurrence()))
        .limit(MAX_OVERVIEW_TASK_SIZE)
        .collect(Collectors.toList());

    final List<Task> overdue = tasks.stream()
        .filter(task -> task.getOccurrence().getDaysUntilNextOccurrence() < 0)
        .sorted(Comparator.comparingLong(task -> task.getOccurrence().getDaysUntilNextOccurrence()))
        .collect(Collectors.toList());

    return Overview.builder()
        .upcoming(upcoming)
        .overdue(overdue)
        .build();
  }

}
