package net.lazars.chores.core.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel {

  private String email;

  private String encodedPassword;

  private String image;

  private List<Permission> permissions = new ArrayList<>();

  private List<Household> households = new ArrayList<>();

  private String chanifyToken;

  private List<NotificationChannel> notificationChannels = new ArrayList<>();
}
