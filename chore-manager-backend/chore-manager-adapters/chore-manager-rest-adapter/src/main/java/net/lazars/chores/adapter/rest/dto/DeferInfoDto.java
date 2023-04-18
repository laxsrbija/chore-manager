package net.lazars.chores.adapter.rest.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeferInfoDto {

  private LocalDate deferDate;
  private UserDto user;
}
