package net.lazars.chores.adapter.rest.controller;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.rest.dto.TaskDto;
import net.lazars.chores.adapter.rest.mapper.DtoMapper;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.port.CrudOperations;
import net.lazars.chores.core.port.in.TaskService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskRestController implements CrudOperations<TaskDto> {

  public final TaskService taskService;

  @Override
  @GetMapping("{id}")
  public TaskDto get(@PathVariable final String id) {
    return DtoMapper.INSTANCE.toTaskDto(taskService.get(id));
  }

  @Override
  @GetMapping
  public List<TaskDto> getAll() {
    return forEach(taskService.getAll(), DtoMapper.INSTANCE::toTaskDto);
  }

  @Override
  @PutMapping
  public TaskDto save(@RequestBody final TaskDto taskDto) {
    final Task task = DtoMapper.INSTANCE.toTask(taskDto);
    return DtoMapper.INSTANCE.toTaskDto(taskService.save(task));
  }

  @Override
  @DeleteMapping("{id}")
  public void delete(@PathVariable final String id) {
    taskService.delete(id);
  }

  @PatchMapping("{taskId}")
  public TaskDto markComplete(
      @PathVariable final String taskId,
      @RequestParam final String userId,
      @DateTimeFormat(iso = ISO.DATE) @RequestParam(required = false)
          final LocalDate dateCompleted) {
    return DtoMapper.INSTANCE.toTaskDto(taskService.markComplete(taskId, userId, dateCompleted));
  }
}