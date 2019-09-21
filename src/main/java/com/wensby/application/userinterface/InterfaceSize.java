package com.wensby.application.userinterface;

import java.util.Objects;
import java.util.StringJoiner;

import static com.wensby.terminablo.Validate.requireNonNegative;

public class InterfaceSize {

  private final int width;
  private final int height;

  public static InterfaceSize of(int width, int height) {
    return new InterfaceSize(width, height);
  }

  public static InterfaceSize between(InterfaceLocation first, InterfaceLocation last) {
    return new InterfaceSize(Math.abs(first.getColumn() - last.getColumn()), Math.abs(first.getRow() - last.getRow()));
  }

  public static final InterfaceSize ZERO = new InterfaceSize(0, 0);

  private InterfaceSize(int width, int height) {
    this.width = requireNonNegative(width);
    this.height = requireNonNegative(height);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public InterfaceSize plus(InterfaceSize size) {
    return new InterfaceSize(width + size.width, height + size.height);
  }

  public InterfaceSize minus(InterfaceSize size) {
    return new InterfaceSize(width - size.width, height - size.height);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", InterfaceSize.class.getSimpleName() + "[", "]")
        .add("width=" + width)
        .add("height=" + height)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InterfaceSize that = (InterfaceSize) o;
    return width == that.width &&
        height == that.height;
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height);
  }
}
