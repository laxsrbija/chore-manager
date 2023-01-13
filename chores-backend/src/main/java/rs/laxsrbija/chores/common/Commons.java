package rs.laxsrbija.chores.common;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Commons {

  public static <T, R> List<R> forEach(final List<T> list, final Function<T, R> function) {
    if (list == null) {
      return new ArrayList<>();
    }

    return list.stream().map(function).toList();
  }

  public static <T, U, R> List<R> forEach(
      final List<T> list, final U parameter, final BiFunction<T, U, R> function) {
    if (list == null) {
      return new ArrayList<>();
    }

    return list.stream().map(item -> function.apply(item, parameter)).toList();
  }
}
