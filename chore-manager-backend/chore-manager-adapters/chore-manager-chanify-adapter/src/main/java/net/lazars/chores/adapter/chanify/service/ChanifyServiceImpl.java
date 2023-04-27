package net.lazars.chores.adapter.chanify.service;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lazars.chores.adapter.chanify.client.ChanifyClient;
import net.lazars.chores.adapter.chanify.model.ChanifyPayload;
import net.lazars.chores.core.model.NotificationChannel;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.out.NotificationSender;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChanifyServiceImpl implements NotificationSender {

  private static final String NOTIFICATION_TITLE = "Chore Manager";

  private final ChanifyClient client;

  @Override
  public NotificationChannel getChannel() {
    return NotificationChannel.CHANIFY;
  }

  @Override
  public void sendTaskReminder(final User user, final Task task, final String title) {
    sendNotification(user, title);
  }

  @Override
  public void sendTaskCompleteByDifferentUserNotification(
      final User user, final Task task, final User otherUser, final String title) {
    sendNotification(user, title);
  }

  private void sendNotification(final User user, final String title) {
    final String chanifyToken = user.getChanifyToken();
    if (chanifyToken == null) {
      log.warn("User '{}' has no Chanify token", user.getId());
      return;
    }

    final ChanifyPayload payload =
        ChanifyPayload.builder().title(NOTIFICATION_TITLE).text(title).build();

    final Call<Void> call = client.publishNotification(chanifyToken, payload);
    final Response<Void> response;
    try {
      response = call.execute();
    } catch (IOException e) {
      log.error("Failed to send Chanify notification", e);
      return;
    }

    if (!response.isSuccessful()) {
      log.error("Failed to send Chanify notification: {}", response.message());
    }
  }
}
