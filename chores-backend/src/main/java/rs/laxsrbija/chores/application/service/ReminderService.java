package rs.laxsrbija.chores.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rs.laxsrbija.chores.domain.Task;

@Slf4j
@Service
@RequiredArgsConstructor
class ReminderService {

  private final TaskService taskService;
  private final EmailService emailService;

  private static boolean shouldRemind(final Task task) {
    if (!task.isEnabled() || task.getHistory().isEmpty()) {
      return false;
    }

    final long daysUntilNextRecurrence = task.getOccurrence().getDaysUntilNextOccurrence();
    final long daysToRemindBeforeRecurrence =
        task.getReminder().getReminderDate().getNumberOfDays();

    return daysToRemindBeforeRecurrence == daysUntilNextRecurrence;
  }

  @Scheduled(cron = "${chores.reminder.cron:0 0 7 * * ?}")
  void checkAllTasksForReminders() {
    taskService.getAll().stream()
        .filter(ReminderService::shouldRemind)
        .forEach(emailService::sendTaskReminder);
  }
}
