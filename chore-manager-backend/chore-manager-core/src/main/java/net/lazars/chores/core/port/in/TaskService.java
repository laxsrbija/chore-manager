package net.lazars.chores.core.port.in;

import java.time.LocalDate;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.port.CrudOperations;

public interface TaskService extends CrudOperations<Task> {

  Task markComplete(String taskId, String userId, LocalDate dateCompleted);
}
