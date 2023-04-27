package net.lazars.chores.core.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.lazars.chores.core.model.NotificationChannel;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.in.NotificationService;
import net.lazars.chores.core.port.out.NotificationSender;

public class NotificationServiceImpl implements NotificationService {

  private final Map<NotificationChannel, NotificationSender> notificationSenders;

  public NotificationServiceImpl(final List<NotificationSender> notificationSenders) {
    this.notificationSenders =
        notificationSenders.stream()
            .collect(Collectors.toMap(NotificationSender::getChannel, Function.identity()));
  }

  @Override
  public void sendTaskReminder(final Task task) {
    final String title =
        "\uD83D\uDCC5 " + task.getName() + " - " + task.getOccurrence().getNextOccurrence();

    final List<User> usersToNotify = task.getReminder().getUsersToNotify();
    for (final User user : usersToNotify) {
      final List<NotificationChannel> notificationChannels = user.getNotificationChannels();
      for (final NotificationChannel notificationChannel : notificationChannels) {
        final NotificationSender notificationSender = notificationSenders.get(notificationChannel);
        notificationSender.sendTaskReminder(user, task, title);
      }
    }
  }

  @Override
  public void sendTaskCompleteByDifferentUserNotification(final Task task, final User otherUser) {
    final String title = "✅ " + task.getName() + " completed by " + otherUser.getName();
    final List<User> usersToNotify =
        task.getReminder().getUsersToNotify().stream()
            .filter(userToNotify -> !userToNotify.getId().equals(otherUser.getId()))
            .toList();

    for (final User user : usersToNotify) {
      final List<NotificationChannel> notificationChannels = user.getNotificationChannels();
      for (final NotificationChannel notificationChannel : notificationChannels) {
        final NotificationSender notificationSender = notificationSenders.get(notificationChannel);
        notificationSender.sendTaskCompleteByDifferentUserNotification(
            user, task, otherUser, title);
      }
    }
  }
}
