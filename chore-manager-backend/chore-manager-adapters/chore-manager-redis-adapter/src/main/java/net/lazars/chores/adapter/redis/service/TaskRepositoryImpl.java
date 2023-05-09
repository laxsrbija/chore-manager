package net.lazars.chores.adapter.redis.service;

import static net.lazars.chores.core.util.ListUtil.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.redis.document.TaskDocument;
import net.lazars.chores.adapter.redis.mapper.TaskMapper;
import net.lazars.chores.adapter.redis.repository.TaskRedisRepository;
import net.lazars.chores.core.model.Item;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.in.ItemService;
import net.lazars.chores.core.port.in.UserService;
import net.lazars.chores.core.port.out.TaskRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskRepositoryImpl extends EntityRepository<Task> implements TaskRepository {

  private final TaskMapper taskMapper;
  private final TaskRedisRepository taskRepository;
  private final UserService userService;
  private final ItemService itemService;

  @Override
  public Task get(final String id) {
    final TaskDocument taskEntity = taskRepository.findById(id).orElseThrow();
    return convertToTask(taskEntity);
  }

  @Override
  public List<Task> getAll() {
    return forEach(taskRepository.findAll(), this::convertToTask);
  }

  @Override
  public void saveEntity(final Task entity) {
    final TaskDocument taskEntity = taskMapper.toTaskDocument(entity);
    taskRepository.save(taskEntity);
  }

  @Override
  public void delete(final String id) {
    taskRepository.deleteById(id);
  }

  private Task convertToTask(final TaskDocument taskEntity) {
    final Item item = itemService.get(taskEntity.getItemId());
    final List<User> users = userService.getAll();

    return taskMapper.toTask(taskEntity, item, users);
  }
}
