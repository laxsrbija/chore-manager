package net.lazars.chores.adapter.sqlite.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lazars.chores.core.model.NotificationChannel;
import net.lazars.chores.core.model.Permission;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(nullable = false)
  private String id;

  private String name;

  private String email;

  @Column(name = "encoded_password")
  private String encodedPassword;

  private String image;

  @ManyToMany
  @JoinTable(
      name = "user_household",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "household_id"))
  private Set<HouseholdEntity> households;

  @ElementCollection
  @CollectionTable(name = "user_permission", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "permission")
  @Enumerated(EnumType.STRING)
  private Set<Permission> permissions;

  @Column(name = "chanify_token")
  private String chanifyToken;

  @ElementCollection
  @CollectionTable(name = "user_notification_channel", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "notification_channel")
  @Enumerated(EnumType.STRING)
  private Set<NotificationChannel> notificationChannels;
}
