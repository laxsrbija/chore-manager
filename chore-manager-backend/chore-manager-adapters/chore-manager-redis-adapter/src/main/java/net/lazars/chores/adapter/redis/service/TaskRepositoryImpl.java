package net.lazars.chores.adapter.redis.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.redis.document.TaskDocument;
import net.lazars.chores.adapter.redis.mapper.TaskMapper;
import net.lazars.chores.adapter.redis.repository.TaskRedisRepository;
import net.lazars.chores.core.helper.FocusHelper;
import net.lazars.chores.core.model.Item;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.in.ItemService;
import net.lazars.chores.core.port.in.UserService;
import net.lazars.chores.core.port.out.TaskRepository;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskRepositoryImpl extends DocumentRepository<Task> implements TaskRepository {

  private final TaskMapper taskMapper;
  private final TaskRedisRepository taskRepository;
  private final UserService userService;
  private final ItemService itemService;

  @Override
  public Task get(final String id) {
    final TaskDocument taskDocument = taskRepository.findById(id).orElseThrow();
    return convertToTasks(List.of(taskDocument)).getFirst();
  }

  @Override
  public List<Task> get(final Collection<String> ids) {
    return convertToTasks(taskRepository.findAllById(ids));
  }

  @Override
  public List<Task> getAll() {
    return convertToTasks(taskRepository.findAll());
  }

  @Override
  public void saveDocument(final Task document) {
    final TaskDocument taskDocument = taskMapper.toTaskDocument(document);
    taskRepository.save(taskDocument);
  }

  @Override
  public void delete(final String id) {
    taskRepository.deleteById(id);
  }

  @Override
  public List<Task> getAllInFocus() {
    final List<TaskDocument> taskDocuments =
        taskRepository.findAll().stream()
            .filter(TaskDocument::isEnabled)
            .map(task -> Pair.of(task, taskMapper.toTask(task, null, List.of())))
            .filter(pair -> FocusHelper.isInFocus(pair.getSecond()))
            .map(Pair::getFirst)
            .toList();

    return convertToTasks(taskDocuments);
  }

  private List<Task> convertToTasks(final List<TaskDocument> tasks) {
    final List<User> users = userService.getAll();

    final Set<String> itemIds =
        tasks.stream().map(TaskDocument::getItemId).collect(Collectors.toSet());
    final Map<String, Item> items = collectById(itemService.get(itemIds));

    return tasks.stream()
        .map(task -> taskMapper.toTask(task, items.get(task.getItemId()), users))
        .toList();
  }
}
