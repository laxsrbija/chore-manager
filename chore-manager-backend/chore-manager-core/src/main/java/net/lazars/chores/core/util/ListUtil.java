package net.lazars.chores.core.util;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListUtil {
  public static <E> List<E> trimList(final List<E> originalList, final int newSize) {
    final int listSize = originalList.size();
    final int startIndex = Math.max(listSize - newSize, 0);

    return originalList.subList(startIndex, listSize);
  }
}
