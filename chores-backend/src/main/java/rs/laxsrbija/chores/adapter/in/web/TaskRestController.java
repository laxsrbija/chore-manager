package rs.laxsrbija.chores.adapter.in.web;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.laxsrbija.chores.application.service.OverviewService;
import rs.laxsrbija.chores.application.service.TaskService;
import rs.laxsrbija.chores.domain.model.dto.Overview;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskRestController {

  private final OverviewService _overviewService;
  private final TaskService _taskService;

  @GetMapping("overview")
  public Overview getOverview() {
    return _overviewService.getOverview();
  }

  @PutMapping("{id}")
  public void markComplete(@PathVariable final String id,
      @RequestParam(required = false) final LocalDate dateCompleted) {
    _taskService.markComplete(id, dateCompleted == null ? LocalDate.now() : dateCompleted);
  }
}
