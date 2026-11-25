package net.lazars.chores.adapter.sqlite.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class TaskEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(nullable = false)
  private String id;

  private String name;

  private String description;

  @Column(name = "date_created")
  @Convert(converter = LocalDateIsoConverter.class)
  private LocalDate dateCreated;

  @ManyToOne
  @JoinColumn(name = "item_id")
  private ItemEntity item;

  @Enumerated(EnumType.STRING)
  @Column(name = "recurrence_type")
  private RecurrenceType recurrenceType;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "frequency", column = @Column(name = "dynamic_frequency")),
    @AttributeOverride(name = "dateUnit", column = @Column(name = "dynamic_date_unit"))
  })
  private DatePeriodEntity dynamicRecurrencePeriod;

  @Column(name = "fixed_recurrence_day")
  private Integer fixedRecurrenceDay;

  @Column(name = "fixed_recurrence_month")
  private Integer fixedRecurrenceMonth;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinTable(
      name = "task_completion_history",
      joinColumns = @JoinColumn(name = "task_id"),
      inverseJoinColumns = @JoinColumn(name = "history_item_id"))
  private Set<CompletionHistoryItemEntity> history;

  @ManyToMany
  @JoinTable(
      name = "task_reminder_users",
      joinColumns = @JoinColumn(name = "task_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private Set<UserEntity> usersToNotify;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "frequency", column = @Column(name = "reminder_frequency")),
    @AttributeOverride(name = "dateUnit", column = @Column(name = "reminder_date_unit"))
  })
  private DatePeriodEntity reminderDate;

  @Embedded
  @AttributeOverride(name = "deferDate", column = @Column(name = "defer_date"))
  private DeferInfoEntity defer;

  private boolean enabled;
}
