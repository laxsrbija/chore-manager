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

  private final TaskInboundPort taskInboundPort;
  private final ItemInboundPort itemInboundPort;
  private final CategoryInboundPort categoryInboundPort;
  private final UserInboundPort userInboundPort;

  public Overview getOverview() {
    final List<Task> tasks = taskInboundPort.getAll();

    final List<Task> upcoming = tasks.stream()
        .filter(Task::isEnabled)
        .filter(task -> task.getOccurrence().getDaysUntilNextOccurrence() >= 0)
        .sorted(Comparator.comparingLong(task -> task.getOccurrence().getDaysUntilNextOccurrence()))
        .collect(Collectors.toList());

    final List<Task> overdue = tasks.stream()
        .filter(Task::isEnabled)
        .filter(task -> task.getOccurrence().getDaysUntilNextOccurrence() < 0)
        .sorted(Comparator.comparingLong(task -> task.getOccurrence().getDaysUntilNextOccurrence()))
        .collect(Collectors.toList());

    return Overview.builder()
        .upcoming(upcoming)
        .overdue(overdue)
        .taskCount(tasks.size())
        .itemCount(itemInboundPort.getAll().size())
        .categoryCount(categoryInboundPort.getAll().size())
        .userCount(userInboundPort.getAll().size())
        .build();
  }

}
