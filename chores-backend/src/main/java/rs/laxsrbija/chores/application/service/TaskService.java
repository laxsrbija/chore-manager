package rs.laxsrbija.chores.application.service;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.laxsrbija.chores.application.port.in.TaskInboundPort;
import rs.laxsrbija.chores.application.port.out.TaskOutboundPort;
import rs.laxsrbija.chores.application.port.out.UserOutboundPort;
import rs.laxsrbija.chores.domain.CompletionHistoryItem;
import rs.laxsrbija.chores.domain.Task;
import rs.laxsrbija.chores.domain.User;

@Service
@RequiredArgsConstructor
class TaskService implements TaskInboundPort {

  private final TaskOutboundPort taskOutboundPort;
  private final UserOutboundPort userOutboundPort;
  private final EmailService emailService;

  @Override
  public Task markComplete(
      final String taskId,
      final String userId,
      final LocalDate dateCompleted) {
    final Task task = get(taskId);
    final User user = userOutboundPort.get(userId);

    task.getHistory().add(CompletionHistoryItem.builder()
        .user(user)
        .dateCompleted(dateCompleted == null ? LocalDate.now() : dateCompleted)
        .build());

    emailService.sendTaskCompleteByDifferentUserNotification(task, user);
    return taskOutboundPort.save(task);
  }

  @Override
  public Task get(final String id) {
    return taskOutboundPort.get(id);
  }

  @Override
  public List<Task> getAll() {
    return taskOutboundPort.getAll();
  }

  @Override
  public Task save(final Task task) {
    retainUnmodifiableFields(task);
    return taskOutboundPort.save(task);
  }

  private void retainUnmodifiableFields(final Task task) {
    if (task.getId() == null) {
      return;
    }

    final Task storedTask = get(task.getId());
    task.setHistory(storedTask.getHistory());
    task.setDateCreated(storedTask.getDateCreated());
  }

  @Override
  public void delete(final String id) {
    taskOutboundPort.delete(id);
  }
}
