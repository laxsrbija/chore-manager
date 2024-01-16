package net.lazars.chores.adapter.rest.controller.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.OverviewDto;
import net.lazars.chores.adapter.rest.dto.TaskDto;
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

  @GetMapping("other")
  public List<TaskDto> getOtherTasks(final Authentication authentication) {
    return overviewService.getOtherTasks(authService.getCurrentUser(authentication));
  }

  @GetMapping("disabled")
  public List<TaskDto> getDisabledTasks(final Authentication authentication) {
    return overviewService.getDisabledTasks(authService.getCurrentUser(authentication));
  }
}
