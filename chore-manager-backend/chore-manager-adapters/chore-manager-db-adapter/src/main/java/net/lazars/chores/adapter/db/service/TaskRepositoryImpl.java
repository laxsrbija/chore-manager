package net.lazars.chores.adapter.db.service;

import static net.lazars.chores.core.util.Commons.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.db.entity.TaskEntity;
import net.lazars.chores.adapter.db.mapper.TaskMapper;
import net.lazars.chores.adapter.db.repository.TaskJsondbRepository;
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
  private final TaskJsondbRepository taskRepository;
  private final UserService userService;
  private final ItemService itemService;

  @Override
  public Task get(final String id) {
    final TaskEntity taskEntity = taskRepository.findById(id);
    return convertToTask(taskEntity);
  }

  @Override
  public List<Task> getAll() {
    return forEach(taskRepository.findAll(), this::convertToTask);
  }

  @Override
  protected void saveEntity(final Task entity) {
    final TaskEntity taskEntity = taskMapper.toTaskEntity(entity);
    taskRepository.save(taskEntity);
  }

  @Override
  public void delete(final String id) {
    taskRepository.delete(id);
  }

  private Task convertToTask(final TaskEntity taskEntity) {
    final Item item = itemService.get(taskEntity.getItemId());
    final List<User> users = userService.getAll();

    return taskMapper.toTask(taskEntity, item, users);
  }
}
