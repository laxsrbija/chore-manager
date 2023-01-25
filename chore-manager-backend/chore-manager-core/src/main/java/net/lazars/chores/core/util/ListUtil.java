package net.lazars.chores.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListUtil {
  public static <E> List<E> trimList(final List<E> originalList, final int newSize) {
    final int listSize = originalList.size();
    final int startIndex = Math.max(listSize - newSize, 0);

    return originalList.subList(startIndex, listSize);
  }

  public static <T, R> List<R> forEach(final List<T> list, final Function<T, R> function) {
    if (list == null) {
      return new ArrayList<>();
    }

    return list.stream().map(function).collect(Collectors.toCollection(ArrayList::new));
  }

  public static <T, U, R> List<R> forEach(
      final List<T> list, final U parameter, final BiFunction<T, U, R> function) {
    if (list == null) {
      return new ArrayList<>();
    }

    return list.stream()
        .map(item -> function.apply(item, parameter))
        .collect(Collectors.toCollection(ArrayList::new));
  }
}
