package net.lazars.chores.core.service;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.core.model.CompletionHistoryItem;
import net.lazars.chores.core.model.DeferInfo;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.in.TaskService;
import net.lazars.chores.core.port.in.UserService;
import net.lazars.chores.core.port.out.EmailSender;
import net.lazars.chores.core.port.out.TaskRepository;
import net.lazars.chores.core.util.ListUtil;

@RequiredArgsConstructor
class TaskServiceImpl implements TaskService {

  private static final int HISTORY_SIZE = 5;

  private final TaskRepository taskRepository;
  private final UserService userService;
  private final EmailSender emailSender;

  @Override
  public Task markComplete(
      final String taskId, final String userId, final LocalDate dateCompleted) {
    final Task task = get(taskId);
    final User user = userService.get(userId);

    final List<CompletionHistoryItem> completionHistory = task.getHistory();
    completionHistory.add(
        CompletionHistoryItem.builder()
            .user(user)
            .dateCompleted(dateCompleted == null ? LocalDate.now() : dateCompleted)
            .build());
    task.setHistory(ListUtil.trimList(completionHistory, HISTORY_SIZE));
    task.setDefer(null);

    new Thread(() -> emailSender.sendTaskCompleteByDifferentUserNotification(task, user)).start();
    return taskRepository.save(task);
  }

  @Override
  public Task defer(final String taskId, final User user, final LocalDate deferDate) {
    final Task task = get(taskId);

    task.setDefer(DeferInfo.builder().deferDate(deferDate).user(user).build());
    return taskRepository.save(task);
  }

  @Override
  public Task get(final String id) {
    return taskRepository.get(id);
  }

  @Override
  public List<Task> getAll() {
    return taskRepository.getAll();
  }

  @Override
  public Task save(final Task task) {
    if (task.getId() == null) {
      return taskRepository.save(task);
    }

    final Task storedTask = get(task.getId());
    storedTask.setName(task.getName());
    storedTask.setDescription(task.getDescription());
    storedTask.setItem(task.getItem());
    storedTask.setRecurrence(task.getRecurrence());
    storedTask.setReminder(task.getReminder());
    storedTask.setEnabled(task.isEnabled());

    return taskRepository.save(storedTask);
  }

  @Override
  public void delete(final String id) {
    taskRepository.delete(id);
  }
}
