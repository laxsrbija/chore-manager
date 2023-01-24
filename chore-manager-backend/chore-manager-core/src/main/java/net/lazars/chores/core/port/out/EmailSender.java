package net.lazars.chores.core.port.out;

import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;

public interface EmailSender {

  void sendTaskReminder(Task task);

  void sendTaskCompleteByDifferentUserNotification(Task task, User user);
}
