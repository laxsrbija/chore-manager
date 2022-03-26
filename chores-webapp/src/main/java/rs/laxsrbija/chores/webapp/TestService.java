package rs.laxsrbija.chores.webapp;

import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import rs.laxsrbija.chores.core.service.TaskService;
import rs.laxsrbija.chores.core.util.RecurrenceUtil;
import rs.laxsrbija.chores.shared.model.CompletionHistoryItem;
import rs.laxsrbija.chores.shared.model.ReminderInfo;
import rs.laxsrbija.chores.shared.model.date.DatePeriod;
import rs.laxsrbija.chores.shared.model.date.DateUnit;
import rs.laxsrbija.chores.shared.model.dto.Task;
import rs.laxsrbija.chores.shared.model.recurrence.FixedRecurrence;

@Service
@Slf4j
public class TestService
{
	@PostConstruct
	public void setup()
	{
		final LocalDate date = LocalDate.of(2022, 3, 10);
		final Task task = Task.builder()
			.history(List.of(CompletionHistoryItem.builder()
				.dateCompleted(date)
				.build()))
			.reminder(ReminderInfo.builder()
				.usersToNotify(List.of("1"))
				.reminderDate(DatePeriod.builder()
					.frequency(20)
					.dateUnit(DateUnit.DAY)
					.build())
				.build())
//			.recurrence(DynamicRecurrence.builder()
//				.frequency(2)
//				.dateUnit(DateUnit.WEEK)
//				.build())
			.recurrence(FixedRecurrence.builder()
				.month(3)
				.day(17)
				.build())
			.build();

//		final DynamicRecurrence dynamicRecurrence = (DynamicRecurrence)task.getRecurrence();
//		final LocalDate nextRecurrence = RecurrenceUtil.getNextRecurrence(date, dynamicRecurrence);

		final FixedRecurrence fixedRecurrence = (FixedRecurrence)task.getRecurrence();
		final LocalDate nextRecurrence = RecurrenceUtil.getNextRecurrence(date, fixedRecurrence);

		log.info(nextRecurrence.toString());
		log.info(TaskService.getDaysUntilNextRecurrence(task) + "");
	}
}
