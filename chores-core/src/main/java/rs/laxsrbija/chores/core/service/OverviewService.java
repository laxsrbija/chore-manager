package rs.laxsrbija.chores.core.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import rs.laxsrbija.chores.shared.model.dto.Overview;
import rs.laxsrbija.chores.shared.model.dto.Task;

@Service
@RequiredArgsConstructor
public class OverviewService
{
	public static final int MAX_OVERVIEW_TASK_SIZE = 10;

	private final TaskService _taskService;

	public Overview getOverview()
	{
		final List<Task> tasks = _taskService.getAllTasks();

		final List<Task> upcoming = tasks.stream()
			.filter(task -> task.getDaysUntilNextRecurrence() >= 0)
			.sorted(Comparator.comparingLong(Task::getDaysUntilNextRecurrence))
			.limit(MAX_OVERVIEW_TASK_SIZE)
			.collect(Collectors.toList());

		final List<Task> overdue = tasks.stream()
			.filter(task -> task.getDaysUntilNextRecurrence() < 0)
			.sorted(Comparator.comparingLong(Task::getDaysUntilNextRecurrence))
			.collect(Collectors.toList());

		return Overview.builder()
			.upcoming(upcoming)
			.overdue(overdue)
			.build();
	}

}
