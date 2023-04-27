package net.lazars.chores.core.port.in;

import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;

public interface NotificationService {

  void sendTaskReminder(Task task);

  void sendTaskCompleteByDifferentUserNotification(Task task, User user);
}
