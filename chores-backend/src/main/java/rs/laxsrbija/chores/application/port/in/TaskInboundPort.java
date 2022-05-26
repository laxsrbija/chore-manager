package rs.laxsrbija.chores.application.port.in;

import java.time.LocalDate;
import rs.laxsrbija.chores.common.CrudOperations;
import rs.laxsrbija.chores.domain.Task;

public interface TaskInboundPort extends CrudOperations<Task> {

  Task markComplete(String taskId, String userId, LocalDate dateCompleted);
}
