package rs.laxsrbija.chores.common;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Commons {

  public static <T, R> List<R> forEach(final List<T> list, final Function<T, R> function) {
    return list.stream()
        .map(function)
        .collect(Collectors.toList());
  }

  public static <T, U, R> List<R> forEach(
      final List<T> list,
      final U parameter,
      final BiFunction<T, U, R> function) {
    return list.stream()
        .map(item -> function.apply(item, parameter))
        .collect(Collectors.toList());
  }
}
