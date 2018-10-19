package com.wensby.terminablo;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class Validate {

  public static class Validator<T extends Number> {

    private final T number;

    public Validator(T number) {
      this.number = requireNonNull(number);
    }

    public T isGreaterThanOrEqualTo(T other) {
      if (number.doubleValue() < other.doubleValue()) {
        throw new IllegalArgumentException(number + " not greater than or equal to " + other);
      }
      return number;
    }
  }

  public static <T extends Number> Validator<T> validateThat(T number) {
    return new Validator(number);
  }

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
