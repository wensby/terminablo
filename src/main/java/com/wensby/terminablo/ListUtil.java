package com.wensby.terminablo;

import java.util.List;

public class ListUtil {

    public static <T> List<T> truncateList(List<T> list, int size) {
        return list.size() > size ? list.subList(0, size) : list;
    }

    public static <T> void removeFirstElements(List<T> list, int count) {
        for (int i = 0; i < count; i++) {
            list.remove(0);
        }
    }
}
