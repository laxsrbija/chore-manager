package net.lazars.chores.adapter.chanify.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChanifyPayload {

  private String title;
  private String text;
  @Builder.Default private Integer sound = 0;
}
