package com.wensby.terminablo;

import java.util.List;

public class ListUtil {

  public static <T> List<T> truncateList(List<T> list, int size) {
    return list.size() > size ? list.subList(0, size) : list;
  }

  public static <T> void removeFirstElements(List<T> list, int count) {
    if (count > 0) {
      list.subList(0, count).clear();
    }
  }
}
