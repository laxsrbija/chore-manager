package net.lazars.chores.core.port.out;

import java.util.List;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.port.CrudOperations;

public interface TaskRepository extends CrudOperations<Task> {

  List<Task> getAllInFocus();
}
