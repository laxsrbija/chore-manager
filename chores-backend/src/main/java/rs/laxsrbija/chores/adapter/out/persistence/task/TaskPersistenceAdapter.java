package rs.laxsrbija.chores.adapter.out.persistence.task;

import static rs.laxsrbija.chores.common.Commons.forEach;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.laxsrbija.chores.adapter.out.persistence.PersistenceAdapter;
import rs.laxsrbija.chores.adapter.out.persistence.entity.TaskEntity;
import rs.laxsrbija.chores.application.port.out.ItemOutboundPort;
import rs.laxsrbija.chores.application.port.out.TaskOutboundPort;
import rs.laxsrbija.chores.application.port.out.UserOutboundPort;
import rs.laxsrbija.chores.domain.Item;
import rs.laxsrbija.chores.domain.Task;
import rs.laxsrbija.chores.domain.User;

@Component
@RequiredArgsConstructor
public class TaskPersistenceAdapter extends PersistenceAdapter<Task> implements TaskOutboundPort {

  private final TaskMapper taskMapper;
  private final TaskRepository taskRepository;
  private final UserOutboundPort userOutboundPort;
  private final ItemOutboundPort itemOutboundPort;

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
    final Item item = itemOutboundPort.get(taskEntity.getItemId());
    final List<User> users = userOutboundPort.getAll();

    return taskMapper.toTask(taskEntity, item, users);
  }
}
