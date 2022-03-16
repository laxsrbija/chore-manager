package rs.laxsrbija.chores.webapp;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import rs.laxsrbija.chores.data.entity.Task;
import rs.laxsrbija.chores.shared.model.CompletionHistoryItem;
import rs.laxsrbija.chores.shared.model.ReminderInfo;
import rs.laxsrbija.chores.shared.model.date.DatePeriod;
import rs.laxsrbija.chores.shared.model.recurrence.DynamicRecurrence;
import rs.laxsrbija.chores.shared.model.date.DateUnit;

@Service
@Slf4j
public class TestService
{
	@PostConstruct
	public void setup()
	{
		final Task task = Task.builder()
			.history(List.of(CompletionHistoryItem.builder()
				.userId("1")
				.dateCompleted(LocalDate.of(2022, 3, 10))
				.build()))
			.reminder(ReminderInfo.builder()
				.usersToNotify(List.of("1"))
				.reminderDate(DatePeriod.builder()
					.frequency(20)
					.dateUnit(DateUnit.DAY)
					.build())
				.build())
			.recurrence(DynamicRecurrence.builder()
				.frequency(2)
				.dateUnit(DateUnit.WEEK)
				.build())
			.build();

		final LocalDate now = LocalDate.now();
		final DynamicRecurrence dynamicRecurrence = (DynamicRecurrence)task.getRecurrence();

		final LocalDate nextRecurrence = now.plusDays(dynamicRecurrence.getNumberOfDays());
		log.info(nextRecurrence.toString());

		final Period period = Period.between(now, nextRecurrence);
		log.info(period.getDays() + "");
		log.info(Period.between(now, now).getDays() + "");

		if (period.getDays() <= task.getReminder().getReminderDate().getNumberOfDays())
		{
			log.info("Notifying users: " + task.getReminder().getUsersToNotify());
		}
	}
}
