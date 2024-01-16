package net.lazars.chores.adapter.rest.controller.rest;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.TaskDto;
import net.lazars.chores.adapter.rest.mapper.DtoMapper;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.port.in.TaskService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('MANAGE')")
@RequestMapping(path = "/api/rest/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskRestController {

  private final TaskService taskService;

  @GetMapping("{id}")
  public TaskDto get(@PathVariable final String id) {
    return DtoMapper.INSTANCE.toTaskDto(taskService.get(id));
  }

  @GetMapping
  public List<TaskDto> getAll() {
    return forEach(taskService.getAll(), DtoMapper.INSTANCE::toTaskDto);
  }

  @PutMapping
  public TaskDto save(@RequestBody final TaskDto taskDto) {
    final Task task = DtoMapper.INSTANCE.toTask(taskDto);
    return DtoMapper.INSTANCE.toTaskDto(taskService.save(task));
  }

  @PostMapping
  public List<TaskDto> saveAll(@RequestBody final List<TaskDto> tasks) {
    return tasks.stream().map(this::save).toList();
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable final String id) {
    taskService.delete(id);
  }
}
