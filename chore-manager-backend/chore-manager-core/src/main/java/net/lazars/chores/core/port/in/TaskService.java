package net.lazars.chores.core.port.in;

import java.time.LocalDate;
import java.util.List;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;
import net.lazars.chores.core.port.CrudOperations;

public interface TaskService extends CrudOperations<Task> {

  Task markComplete(String taskId, String userId, LocalDate dateCompleted);

  Task defer(String taskId, User user, LocalDate deferDate);

  List<Task> getAllInFocus();
}
