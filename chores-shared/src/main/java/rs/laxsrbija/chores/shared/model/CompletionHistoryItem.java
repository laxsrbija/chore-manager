package rs.laxsrbija.chores.shared.model;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompletionHistoryItem
{
	private String userId;
	private LocalDate dateCompleted;
}
