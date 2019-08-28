package com.wensby.application.userinterface;

import java.util.List;

public class LinuxTerminalRenderCommandFactory implements TerminalRenderCommandFactory {

  @Override
  public TerminalRenderCommand createCommand(List<PositionedTerminalCharacter> characters) {
    if (characters.isEmpty()) {
      return new LinuxTerminalRenderCommandBuilder().build();
    }
    else {
      var commandBuilder = new LinuxTerminalRenderCommandBuilder();
      characters.stream().sorted(this::compareLocation).forEach(commandBuilder::printCharacter);
      return commandBuilder.build();
    }
  }

  private int compareLocation(PositionedTerminalCharacter a, PositionedTerminalCharacter b) {
    var aLocation = a.getLocation();
    var bLocation = b.getLocation();
    int rowComparison = aLocation.getRow() - bLocation.getRow();
    if (rowComparison == 0) {
      return aLocation.getColumn() - bLocation.getColumn();
    }
    else {
      return rowComparison;
    }
  }

  @Override
  public TerminalRenderCommand createMoveCursorCommand(int row, int column) {
    return new LinuxMoveCursorCommand(TerminalCoordinates.of(row, column));
  }

  @Override
  public TerminalRenderCommand createClearScreenCommand() {
    return new LinuxClearScreenRenderCommand();
  }

  @Override
  public TerminalRenderCommand createHideCursorCommand() {
    return new LinuxHideCursorCommand();
  }

  @Override
  public TerminalRenderCommand createShowCursorCommand() {
    return new LinuxShowCursorCommand();
  }
}
