package net.lazars.chores.core.port.out;

import net.lazars.chores.core.model.NotificationChannel;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;

public interface NotificationSender {

  NotificationChannel getChannel();

  void sendTaskReminder(User user, Task task, String title);

  void sendTaskCompleteByDifferentUserNotification(
      User user, Task task, User otherUser, String title);
}
