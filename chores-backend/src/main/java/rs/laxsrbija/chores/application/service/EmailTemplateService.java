package rs.laxsrbija.chores.application.service;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import rs.laxsrbija.chores.domain.Task;
import rs.laxsrbija.chores.domain.User;

@Service
@RequiredArgsConstructor
class EmailTemplateService {

  public static final String TASK_REMINDER_TEMPLATE = "templates/task-reminder.html";
  private final TemplateEngine templateEngine;

  @Value("${chores.server}")
  private String server;

  public String parseEmailTemplate(final Task task, final User user) {
    final Map<String, Object> variables =
        Map.of(
            "server", server,
            "task", task,
            "user", user);

    final Context context = new Context();
    context.setVariables(variables);

    return templateEngine.process(TASK_REMINDER_TEMPLATE, context);
  }
}
