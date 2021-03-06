package rs.laxsrbija.chores.adapter.in.web.rest;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.laxsrbija.chores.application.port.in.TaskInboundPort;
import rs.laxsrbija.chores.common.CrudOperations;
import rs.laxsrbija.chores.domain.Task;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskRestController implements CrudOperations<Task> {

  public final TaskInboundPort taskInboundPort;

  @Override
  @GetMapping("{id}")
  public Task get(@PathVariable final String id) {
    return taskInboundPort.get(id);
  }

  @Override
  @GetMapping
  public List<Task> getAll() {
    return taskInboundPort.getAll();
  }

  @Override
  @PutMapping
  public Task save(@RequestBody final Task object) {
    return taskInboundPort.save(object);
  }

  @Override
  @DeleteMapping("{id}")
  public void delete(@PathVariable final String id) {
    taskInboundPort.delete(id);
  }

  @PatchMapping("{taskId}")
  public Task markComplete(
      @PathVariable final String taskId,
      @RequestParam final String userId,
      @DateTimeFormat(iso = ISO.DATE) @RequestParam(required = false)
          final LocalDate dateCompleted) {
    return taskInboundPort.markComplete(taskId, userId, dateCompleted);
  }
}
