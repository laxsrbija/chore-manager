package net.lazars.chores.adapter.rest.controller;

import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.OverviewDto;
import net.lazars.chores.adapter.rest.service.OverviewService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/service/overview", produces = MediaType.APPLICATION_JSON_VALUE)
public class OverviewRestController {

  private final OverviewService overviewService;

  @GetMapping
  public OverviewDto getOverview() {
    return overviewService.getOverview();
  }
}
