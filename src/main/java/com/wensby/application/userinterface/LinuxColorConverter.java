package com.wensby.application.userinterface;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public class LinuxColorConverter {

  private static final Map<Color, List<Integer>> BACKGROUND_COLOR_CODES = Map.ofEntries(
      entry(Color.RED, List.of(41)),
      entry(Color.BLUE, List.of(44)),
      entry(Color.YELLOW, List.of(43)),
      entry(Color.DARK_GRAY, List.of(100))
  );
  private static final Map<Color, List<Integer>> FOREGROUND_COLOR_CODES = Map.ofEntries(
      entry(Color.BLACK, List.of(30)),
      entry(Color.RED, List.of(31)),
      entry(Color.BLUE, List.of(34)),
      entry(Color.YELLOW, List.of(33))
  );
  public static final List<Integer> LIST_OF_ONE = List.of(1);

  public static List<Integer> getBackgroundColorCode(Color color) {
    return Optional.ofNullable(color)
        .map(c -> BACKGROUND_COLOR_CODES.getOrDefault(c, List.of(48, 2, c.getRed(), c.getGreen(), c.getBlue())))
        .orElse(LIST_OF_ONE);
  }

  public static List<Integer> getForegroundColorCode(Color color) {
    return Optional.ofNullable(color)
        .map(c -> FOREGROUND_COLOR_CODES.getOrDefault(c, List.of(38, 2, c.getRed(), c.getGreen(), c.getBlue())))
        .orElse(LIST_OF_ONE);
  }
}
