package rs.laxsrbija.chores.adapter.in.web.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.laxsrbija.chores.adapter.in.web.model.Overview;
import rs.laxsrbija.chores.application.port.in.CategoryInboundPort;
import rs.laxsrbija.chores.application.port.in.ItemInboundPort;
import rs.laxsrbija.chores.application.port.in.TaskInboundPort;
import rs.laxsrbija.chores.application.port.in.UserInboundPort;
import rs.laxsrbija.chores.domain.Task;

@Service
@RequiredArgsConstructor
public class OverviewService {

  private static final Comparator<Task> OCCURRENCE_COMPARATOR =
      Comparator.comparingLong(task -> task.getOccurrence().getDaysUntilNextOccurrence());

  private final TaskInboundPort taskInboundPort;
  private final ItemInboundPort itemInboundPort;
  private final CategoryInboundPort categoryInboundPort;
  private final UserInboundPort userInboundPort;

  public Overview getOverview() {
    final List<Task> tasks = taskInboundPort.getAll();

    final List<Task> upcoming =
        tasks.stream()
            .filter(Task::isEnabled)
            .filter(task -> task.getOccurrence().getDaysUntilNextOccurrence() >= 0)
            .sorted(OCCURRENCE_COMPARATOR.thenComparingInt(task -> task.getHistory().size()))
            .collect(Collectors.toList());

    final List<Task> overdue =
        tasks.stream()
            .filter(Task::isEnabled)
            .filter(task -> task.getOccurrence().getDaysUntilNextOccurrence() < 0)
            .sorted(OCCURRENCE_COMPARATOR)
            .collect(Collectors.toList());

    final List<Task> disabled = tasks.stream()
        .filter(task -> !task.isEnabled())
        .sorted(Comparator.comparing(Task::getName))
        .collect(Collectors.toList());

    return Overview.builder()
        .upcoming(upcoming)
        .overdue(overdue)
        .disabled(disabled)
        .taskCount(tasks.size())
        .itemCount(itemInboundPort.getAll().size())
        .categoryCount(categoryInboundPort.getAll().size())
        .userCount(userInboundPort.getAll().size())
        .build();
  }

}
