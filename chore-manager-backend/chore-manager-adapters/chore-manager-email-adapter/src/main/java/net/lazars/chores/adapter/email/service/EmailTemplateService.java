package net.lazars.chores.adapter.email.service;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import net.lazars.chores.core.model.Task;
import net.lazars.chores.core.model.User;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
class EmailTemplateService {

  public static final String TASK_REMINDER_TEMPLATE = "task-reminder.html";
  public static final String TASK_COMPLETE_BY_DIFFERENT_USER_TEMPLATE =
      "task-complete-different-user.html";
  private final TemplateEngine templateEngine;

  public String parseTaskReminderEmailTemplate(final Task task, final User user) {
    final Map<String, Object> variables =
        Map.of(
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
