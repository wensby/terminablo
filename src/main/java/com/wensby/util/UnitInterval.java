package com.wensby.util;

import static com.wensby.terminablo.Validate.requireUnitInterval;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class UnitInterval {

  private final double value;

  public static UnitInterval of(double value) {
    return new UnitInterval(value);
  }

  public static UnitInterval truncate(double value) {
    return new UnitInterval(max(0.0, min(1.0, value)));
  }

  private UnitInterval(double value) {
    this.value = requireUnitInterval(value);
  }

  public double getValue() {
    return value;
  }

  public boolean isZero() {
    return value == 0.0;
  }

  public int toIntRoundedDown(int max) {
    return (int) ((long)max * value);
  }
}
