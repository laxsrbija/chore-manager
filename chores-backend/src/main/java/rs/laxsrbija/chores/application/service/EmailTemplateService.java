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

  public static final String TASK_REMINDER_TEMPLATE = "task-reminder.html";
  public static final String TASK_COMPLETE_BY_DIFFERENT_USER_TEMPLATE =
      "task-complete-different-user.html";
  private final TemplateEngine templateEngine;

  @Value("${chores.server}")
  private String server;

  public String parseTaskReminderEmailTemplate(final Task task, final User user) {
    final Map<String, Object> variables =
        Map.of(
            "server", server,
            "task", task,
            "user", user);

    return parseTemplate(TASK_REMINDER_TEMPLATE, variables);
  }

  public String parseTaskCompleteByDifferentUserEmailTemplate(
      final Task task, final User user, final User otherUser) {
    final Map<String, Object> variables =
        Map.of(
            "otherUser", otherUser,
            "task", task,
            "user", user);

    return parseTemplate(TASK_COMPLETE_BY_DIFFERENT_USER_TEMPLATE, variables);
  }

  private String parseTemplate(final String templateName, final Map<String, Object> variables) {
    final Context context = new Context();
    context.setVariables(variables);

    return templateEngine.process(templateName, context);
  }
}
