package net.lazars.chores.adapter.scheduler.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.port.in.TaskService;
import net.lazars.chores.core.port.out.EmailSender;
import net.lazars.chores.core.util.Commons;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class ReminderService {

  private final TaskService taskService;
  private final EmailSender emailService;

  private static boolean shouldRemind(final Task task) {
    if (!task.isEnabled() || task.getHistory().isEmpty()) {
      return false;
    }

    final long daysUntilNextOccurrence = task.getOccurrence().getDaysUntilNextOccurrence();
    final long daysToRemindBeforeOccurrence =
        Commons.getNumberOfDays(task.getReminder().getReminderDate());

    return daysToRemindBeforeOccurrence == daysUntilNextOccurrence;
  }

  @Scheduled(cron = "${chores.reminder.cron:0 0 7 * * ?}")
  void checkAllTasksForReminders() {
    log.info("Starting the daily reminder job");
    taskService.getAll().stream()
        .filter(ReminderService::shouldRemind)
        .forEach(emailService::sendTaskReminder);
  }
}
