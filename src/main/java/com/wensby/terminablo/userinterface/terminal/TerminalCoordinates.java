package com.wensby.terminablo.userinterface.terminal;

import com.wensby.terminablo.Validate;
import java.util.Objects;

public class TerminalCoordinates {

  private final int row;
  private final int column;

  private TerminalCoordinates(int row, int column) {
    Validate.notNegative(row, "Terminal coordinates row negative: " + row);
    Validate.notNegative(column, "Terminal coordinates column negative: " + column);
    this.row = row;
    this.column = column;
  }

  public static TerminalCoordinates of(int row, int column) {
    return new TerminalCoordinates(row, column);
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
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
    return "TerminalCoordinates{" +
        "row=" + row +
        ", column=" + column +
        '}';
  }
}
