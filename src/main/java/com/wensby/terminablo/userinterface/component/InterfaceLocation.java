package com.wensby.terminablo.userinterface.component;

import static java.util.Objects.hash;

public class InterfaceLocation {

  private static final InterfaceLocation ORIGIN = of(0, 0);

  private final int row;
  private final int column;

  private InterfaceLocation(int column, int row) {
    this.row = row;
    this.column = column;
  }

  public static InterfaceLocation of(int column, int row) {
    return new InterfaceLocation(column, row);
  }

  public static InterfaceLocation atOrigin() {
    return ORIGIN;
  }

  public InterfaceLocation plus(InterfaceLocation position) {
    return new InterfaceLocation(column + position.column, row + position.row);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InterfaceLocation that = (InterfaceLocation) o;
    return row == that.row &&
        column == that.column;
  }

  @Override
  public int hashCode() {
    return hash(row, column);
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }
}
