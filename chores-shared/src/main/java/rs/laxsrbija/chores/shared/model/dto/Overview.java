package rs.laxsrbija.chores.shared.model.dto;

import java.util.List;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class Overview
{
	private List<Task> upcoming;
	private List<Task> overdue;
}
