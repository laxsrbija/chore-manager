package net.lazars.chores.adapter.sqlite.service;

import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.adapter.sqlite.entity.TaskEntity;
import net.lazars.chores.adapter.sqlite.mapper.EntityMapper;
import net.lazars.chores.adapter.sqlite.repository.TaskJpaRepository;
import net.lazars.chores.core.helper.FocusHelper;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.port.TaskRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryAdapter implements TaskRepository {

  private final TaskJpaRepository repository;
  private final EntityMapper mapper;

  @Override
  @Transactional(readOnly = true)
  public Task get(final String id) {
    return mapper.toTask(repository.findById(id).orElseThrow());
  }

  @Override
  @Transactional(readOnly = true)
  public List<Task> get(final Collection<String> ids) {
    return repository.findAllById(ids).stream().map(mapper::toTask).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Task> getAll() {
    return repository.findAll().stream().map(mapper::toTask).toList();
  }

  @Override
  @Transactional
  public Task save(final Task task) {
    return mapper.toTask(repository.save(mapper.toTaskEntity(task)));
  }

  @Override
  @Transactional
  public void delete(final String id) {
    repository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Task> getAllInFocus() {
    return repository.findAll().stream()
        .filter(TaskEntity::isEnabled)
        .map(mapper::toTask)
        .filter(FocusHelper::isInFocus)
        .toList();
  }
}
