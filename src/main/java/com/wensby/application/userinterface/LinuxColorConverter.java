package com.wensby.application.userinterface;

import java.awt.*;

public class LinuxColorConverter {

  public static int getBackgroundColorCode(Color color) {
    if (color == null) {
      return 1;
    }
    if (color.equals(Color.RED)) {
      return 41;
    } else if (color.equals(Color.BLUE)) {
      return 44;
    } else {
      return 1;
    }
  }

  public static int getForegroundColorCode(Color color) {
    if (color == null) {
      return 1;
    }
    if (color.equals(Color.RED)) {
      return 31;
    } else if (color.equals(Color.BLUE)) {
      return 34;
    } else {
      return 1;
    }
  }
}