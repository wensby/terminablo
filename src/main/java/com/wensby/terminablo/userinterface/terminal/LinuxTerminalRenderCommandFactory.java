package com.wensby.terminablo.userinterface.terminal;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.userinterface.TerminalCharacter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LinuxTerminalRenderCommandFactory implements TerminalRenderCommandFactory {

  @Override
  public TerminalRenderCommand createCommand(TerminalCharacter[][] characters) {
    var commandBuilder = new TerminalRenderCommandBuilder();
    final int rowCount = characters.length;
    for (int y = 0; y < rowCount; y++) {
      int rowLength = characters[0].length;
      boolean cursorAtCorrectPosition = false;
      for (int x = 0; x < rowLength; x++) {
        var character = characters[y][x];
        if (character != null) {
          if (!cursorAtCorrectPosition) {
            commandBuilder.addCommand(createMoveCursorCommand(y, x));
            cursorAtCorrectPosition = true;
          }
          commandBuilder.addTerminalCharacter(character);
          if (character.isLong()) {
            commandBuilder.addCommand(createMoveCursorCommand(y, x + 1));
          }
        }
        else {
          cursorAtCorrectPosition = false;
        }
      }
    }
    return commandBuilder.build();
  }

  @Override
  public TerminalRenderCommand createCommand(Map<InterfaceLocation, ? extends TerminalCharacter> characters) {
    if (characters.isEmpty()) {
      return new TerminalRenderCommandBuilder().build();
    }
    else {
      var sortedEntries = sortEntriesByLocation(characters);
      var commandBuilder = new TerminalRenderCommandBuilder();
      var cursorLocation = sortedEntries.get(0).getKey();
      commandBuilder.addCommand(createMoveCursorCommand(cursorLocation));
      for (var entry : sortedEntries) {
        var location = entry.getKey();
        var terminalCharacter = entry.getValue();
        if (!location.equals(cursorLocation)) {
          commandBuilder.addCommand(createMoveCursorCommand(location));
          cursorLocation = location;
        }
        commandBuilder.addTerminalCharacter(terminalCharacter);
        cursorLocation = cursorLocation.plus(InterfaceLocation.of(terminalCharacter.isLong() ? 2 : 1, 0));
      }
      return commandBuilder.build();
    }
  }

  private TerminalRenderCommand createMoveCursorCommand(InterfaceLocation cursorLocation) {
    return createMoveCursorCommand(cursorLocation.getRow(), cursorLocation.getColumn());
  }

  private List<? extends Map.Entry<InterfaceLocation, ? extends TerminalCharacter>> sortEntriesByLocation(Map<InterfaceLocation, ? extends TerminalCharacter> characters) {
    return characters.entrySet().stream().sorted((a, b) -> {
          if (a.getKey().getRow() == b.getKey().getRow()) {
            return a.getKey().getColumn() - b.getKey().getColumn();
          }
          return a.getKey().getRow() - b.getKey().getRow();
        }).collect(Collectors.toList());
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
