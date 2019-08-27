package com.wensby.application.userinterface;

import java.util.Objects;
import java.util.StringJoiner;

import static com.wensby.terminablo.Validate.requireNonNegative;

public class TerminalCoordinates {

  private final int row;
  private final int column;

  public static TerminalCoordinates of(int row, int column) {
    return new TerminalCoordinates(row, column);
  }

  private TerminalCoordinates(int row, int column) {
    this.row = requireNonNegative(row);
    this.column = requireNonNegative(column);
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public TerminalCoordinates plus(int row, int column) {
    return new TerminalCoordinates(this.row + row, this.column + column);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerminalCoordinates that = (TerminalCoordinates) o;
    return row == that.row &&
        column == that.column;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, column);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", TerminalCoordinates.class.getSimpleName() + "{", "}")
        .add("row=" + row)
        .add("column=" + column)
        .toString();
  }
}
