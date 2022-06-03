package rs.laxsrbija.chores.adapter.in.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.laxsrbija.chores.adapter.in.web.model.Overview;
import rs.laxsrbija.chores.adapter.in.web.service.OverviewService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/overview", produces = MediaType.APPLICATION_JSON_VALUE)
public class OverviewRestController {

  private final OverviewService overviewService;

  @GetMapping
  public Overview getOverview() {
    return overviewService.getOverview();
  }
}
