package com.wensby.terminablo.userinterface.terminal;

import com.wensby.userinterface.TerminalCharacter;

public class LinuxTerminalRenderCommandFactory implements TerminalRenderCommandFactory {

  @Override
  public TerminalRenderCommand createCommand(TerminalCharacter[][] characters) {
    var commandBuilder = new TerminalRenderCommandBuilder();
    commandBuilder.addCommand(createClearScreenCommand());
    final int rowCount = characters.length;
    for (int y = 0; y < rowCount; y++) {
      int rowLength = characters[0].length;
      for (int x = 0; x < rowLength; x++) {
        var character = characters[y][x];
        if (character != null) {
          commandBuilder.addCommand(createMoveCursorCommand(y, x));
          commandBuilder.addTerminalCharacter(character);
          if (character.isLong()) {
            commandBuilder.addCommand(createMoveCursorCommand(y, x + 1));
          }
        }
      }
    }
    return commandBuilder.build();
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
