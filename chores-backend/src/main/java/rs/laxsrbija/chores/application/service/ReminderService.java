package rs.laxsrbija.chores.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rs.laxsrbija.chores.domain.model.dto.Task;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReminderService {

  private final TaskService _taskService;
  private final EmailService _emailService;

  private static boolean shouldRemind(final Task task) {
    if (!task.isEnabled() || task.getHistory().isEmpty()) {
      return false;
    }

    final long daysUntilNextRecurrence = task.getDaysUntilNextRecurrence();
    final long daysToRemindBeforeRecurrence = task.getReminder().getReminderDate()
        .getNumberOfDays();

    return daysToRemindBeforeRecurrence == daysUntilNextRecurrence;
  }

  //	@Scheduled(cron = "0 0 7 * * ?")
  public void checkAllTasksForReminders() {
    _taskService.getAllTasks().stream()
        .filter(ReminderService::shouldRemind)
        .forEach(_emailService::sendReminder);
  }
}
