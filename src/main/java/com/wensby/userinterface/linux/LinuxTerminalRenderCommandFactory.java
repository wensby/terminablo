package com.wensby.userinterface.linux;

import com.wensby.userinterface.*;

import java.util.List;

public class LinuxTerminalRenderCommandFactory implements TerminalRenderCommandFactory {

  @Override
  public TerminalRenderCommand createCommand(List<PositionedTerminalCharacter> characters) {
    if (characters.isEmpty()) {
      return new LinuxTerminalRenderCommandBuilder().build();
    }
    else {
      var commandBuilder = new LinuxTerminalRenderCommandBuilder();
      for (var positionedCharacter : characters) {
        var location = positionedCharacter.getLocation();
        var terminalCharacter = positionedCharacter.getCharacter();
        commandBuilder.moveCursor(TerminalCoordinates.of(location.getRow(), location.getColumn()));
        commandBuilder.printCharacter(terminalCharacter);
      }
      return commandBuilder.build();
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
