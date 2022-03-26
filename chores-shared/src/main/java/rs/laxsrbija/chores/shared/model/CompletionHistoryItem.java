package rs.laxsrbija.chores.shared.model;

import java.time.LocalDate;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletionHistoryItem
{
	private LocalDate dateCompleted;
}
