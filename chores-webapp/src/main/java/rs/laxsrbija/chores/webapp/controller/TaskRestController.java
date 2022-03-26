package rs.laxsrbija.chores.webapp.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import rs.laxsrbija.chores.core.service.OverviewService;
import rs.laxsrbija.chores.shared.model.dto.Overview;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskRestController
{
	private final OverviewService _overviewService;

	@GetMapping("overview")
	public Overview getOverview()
	{
		return _overviewService.getOverview();
	}
}
