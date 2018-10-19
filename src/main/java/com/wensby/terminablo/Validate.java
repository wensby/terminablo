package com.wensby.terminablo;

public class Validate {

  public static float requireUnitInterval(float number) {
    if (number < 0.0f || number > 1.0f) {
      throw new IllegalArgumentException("Argument not unit interval: " + number);
    }
    return number;
  }

  public static double requireUnitInterval(double number) {
    if (number < 0.0 || number > 1.0) {
      throw new IllegalArgumentException("Argument not unit interval: " + number);
    }
    return number;
  }

  public static long requireNonNegative(long integer) {
    if (integer < 0) {
      throw new IllegalArgumentException("Argument negative");
    }
    return integer;
  }

  public static int requireNonNegative(int integer) {
    if (integer < 0) {
      throw new IllegalArgumentException("Argument negative");
    }
    return integer;
  }
}
