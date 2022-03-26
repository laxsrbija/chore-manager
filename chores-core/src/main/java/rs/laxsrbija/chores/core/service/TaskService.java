package rs.laxsrbija.chores.core.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import rs.laxsrbija.chores.core.mapper.TaskMapper;
import rs.laxsrbija.chores.core.util.RecurrenceUtil;
import rs.laxsrbija.chores.data.repository.TaskRepository;
import rs.laxsrbija.chores.shared.model.dto.Object;
import rs.laxsrbija.chores.shared.model.dto.Task;
import rs.laxsrbija.chores.shared.model.recurrence.*;

@Service
@RequiredArgsConstructor
public class TaskService
{
	private final ObjectService _objectService;
	private final TaskRepository _taskRepository;
	private final TaskMapper _taskMapper;

	public List<Task> getAllTasks()
	{
		return _taskRepository.findAll().stream()
			.map(task -> {
				final Object object = _objectService.getObject(task.getObjectId());
				return _taskMapper.toTask(task, object);
			})
			.collect(Collectors.toList());
	}

	public static LocalDate getNextRecurrence(final Task task)
	{
		final LocalDate latestCompletion =
			task.getHistory() != null ? task.getHistory().get(task.getHistory().size() - 1).getDateCompleted() : null;

		final Recurrence recurrence = task.getRecurrence();
		if (recurrence instanceof DynamicRecurrence)
		{
			return RecurrenceUtil.getNextRecurrence(latestCompletion, (DynamicRecurrence)recurrence);
		}
		else
		{
			return RecurrenceUtil.getNextRecurrence(latestCompletion, (FixedRecurrence)recurrence);
		}
	}

	public static long getDaysUntilNextRecurrence(final Task task)
	{
		final LocalDate nextRecurrence = getNextRecurrence(task);
		return ChronoUnit.DAYS.between(LocalDate.now(), nextRecurrence);
	}
}
