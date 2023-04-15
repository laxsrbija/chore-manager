package net.lazars.chores.adapter.rest.controller.service;

import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.OverviewDto;
import net.lazars.chores.adapter.rest.service.AuthService;
import net.lazars.chores.adapter.rest.service.OverviewService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/service/overview", produces = MediaType.APPLICATION_JSON_VALUE)
public class OverviewRestController {

  private final OverviewService overviewService;
  private final AuthService authService;

  @GetMapping
  public OverviewDto getOverview(final Authentication authentication) {
    return overviewService.getOverview(authService.getCurrentUser(authentication));
  }
}
