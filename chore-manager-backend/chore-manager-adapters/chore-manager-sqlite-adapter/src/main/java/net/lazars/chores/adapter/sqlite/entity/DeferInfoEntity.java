package net.lazars.chores.adapter.sqlite.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DeferInfoEntity {

  @Column(name = "defer_date")
  @Convert(converter = LocalDateIsoConverter.class)
  private LocalDate deferDate;

  @ManyToOne
  @JoinColumn(name = "deferred_by_user_id")
  private UserEntity user;
}
