package com.wensby.application.userinterface;

import java.util.Objects;
import java.util.StringJoiner;

public class LinuxMoveCursorCommand implements MoveCursorCommand {

  private final TerminalCoordinates coordinates;

  public LinuxMoveCursorCommand(TerminalCoordinates coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  public TerminalCoordinates getCoordinates() {
    return coordinates;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", LinuxMoveCursorCommand.class.getSimpleName() + "{", "}")
        .add("coordinates=" + coordinates)
        .toString();
  }

  @Override
  public String toRenderString() {
    int row = coordinates.getRow() + 1;
    int column = coordinates.getColumn() + 1;
    return String.format("%c[%d;%df", Ansi.ESCAPE, row, column);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LinuxMoveCursorCommand that = (LinuxMoveCursorCommand) o;
    return Objects.equals(coordinates, that.coordinates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(coordinates);
  }
}
