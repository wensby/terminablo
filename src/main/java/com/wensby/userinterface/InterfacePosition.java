package com.wensby.userinterface;

import java.util.Objects;
import java.util.StringJoiner;

public class InterfacePosition {

  private static final InterfacePosition ORIGIN = new InterfacePosition(0, 0);

  private final int column;
  private final int row;

  private InterfacePosition(int column, int row) {
    this.column = column;
    this.row = row;
  }

  public static InterfacePosition atOrigin() {
    return ORIGIN;
  }

  public static InterfacePosition of(int column, int row) {
    return new InterfacePosition(column, row);
  }

  public InterfacePosition plus(InterfacePosition position) {
    return new InterfacePosition(column + position.column, row + position.row);
  }

  public int getColumn() {
    return column;
  }

  public int getRow() {
    return row;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InterfacePosition that = (InterfacePosition) o;
    return column == that.column &&
        row == that.row;
  }

  @Override
  public int hashCode() {
    return Objects.hash(column, row);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", InterfacePosition.class.getSimpleName() + "[", "]")
        .add("column=" + column)
        .add("row=" + row)
        .toString();
  }
}
