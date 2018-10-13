package com.wensby.terminablo.userinterface.terminal;

import com.wensby.userinterface.LinuxTerminalCharacterFactory;
import com.wensby.userinterface.TerminalCharacter;

public class LinuxTerminalRenderCommandFactory implements TerminalRenderCommandFactory {

  private final LinuxTerminalCharacterFactory terminalCharacterFactory;

  public LinuxTerminalRenderCommandFactory(LinuxTerminalCharacterFactory terminalCharacterFactory) {
    this.terminalCharacterFactory = terminalCharacterFactory;
  }

  @Override
  public TerminalRenderCommand createCommand(TerminalCharacter[][] characters) {
    TerminalRenderCommandBuilder commandBuilder = new TerminalRenderCommandBuilder();
    commandBuilder.addCommand(createClearScreenCommand());
    for (int y = 0; y < characters.length; y++) {
      int rowLength = characters[y].length;
      for (int x = 0; x < rowLength; x++) {
        TerminalCharacter character = characters[y][x];
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
}
