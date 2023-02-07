package net.lazars.chores.adapter.rest.controller;

import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.OverviewDto;
import net.lazars.chores.adapter.rest.service.OverviewService;
import net.lazars.chores.core.port.in.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/overview", produces = MediaType.APPLICATION_JSON_VALUE)
public class OverviewRestController {

  private final OverviewService overviewService;
  private final UserService userService;

  @GetMapping
  public OverviewDto getOverview() {
    return overviewService.getOverview();
  }
}
