package net.lazars.chores.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private UUID id;
  private String name;

  private String email;

  private String encodedPassword;

  private String image;

  @Builder.Default private List<Permission> permissions = new ArrayList<>();

  @Builder.Default private List<Household> households = new ArrayList<>();

  private String chanifyToken;

  @Builder.Default private List<NotificationChannel> notificationChannels = new ArrayList<>();
}
