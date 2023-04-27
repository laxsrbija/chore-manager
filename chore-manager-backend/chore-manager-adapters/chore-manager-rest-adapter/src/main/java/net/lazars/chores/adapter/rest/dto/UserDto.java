package net.lazars.chores.adapter.rest.dto;

import java.util.List;import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.lazars.chores.core.model.NotificationChannel;
import net.lazars.chores.core.model.Permission;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {

  private String email;

  private String image;

  private List<Permission> permissions;

  private List<HouseholdDto> households;

  private String chanifyToken;

  private List<NotificationChannel> notificationChannels;
}
