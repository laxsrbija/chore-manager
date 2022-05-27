package rs.laxsrbija.chores.adapter.in.web;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rs.laxsrbija.chores.application.port.in.TaskInboundPort;
import rs.laxsrbija.chores.application.port.in.UserInboundPort;
import rs.laxsrbija.chores.domain.BaseDto;

@Controller
@RequiredArgsConstructor
public class IndexController {

  public final TaskInboundPort taskInboundPort;
  public final UserInboundPort userInboundPort;

  @GetMapping("complete")
  public String markComplete(
      @RequestParam final String taskId, @RequestParam final String userId, final Model model) {
    final Map<String, BaseDto> attributes =
        Map.of(
            "task", taskInboundPort.get(taskId),
            "user", userInboundPort.get(userId));
    model.addAllAttributes(attributes);

    // Do work in background
    new Thread(() -> taskInboundPort.markComplete(taskId, userId, null)).start();

    return "task-complete";
  }
}
