package net.lazars.chores.adapter.rest.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.lazars.chores.adapter.rest.service.UserAuthenticationService;
import net.lazars.chores.core.exception.ChoreManagerException;
import net.lazars.chores.core.model.Household;
import net.lazars.chores.core.model.User;
import org.springframework.security.core.Authentication;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationUtils {

  public static String getAuthenticatedUserEmail(final Authentication authentication) {
    final Object principal = authentication.getPrincipal();
    if (principal instanceof UserAuthenticationService.User user) {
      return user.getUsername();
    }

    throw new ChoreManagerException("Unable to determine logged-in user's email");
  }

  public static boolean isUserInHousehold(final User user, final Household household) {
    return user.getHouseholds().stream().map(Household::getId).toList().contains(household.getId());
  }
}
