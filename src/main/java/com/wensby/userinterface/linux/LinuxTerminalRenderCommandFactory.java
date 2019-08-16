package com.wensby.userinterface.linux;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.userinterface.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LinuxTerminalRenderCommandFactory implements TerminalRenderCommandFactory {

  @Override
  public TerminalRenderCommand createCommand(Map<InterfaceLocation, TerminalCharacter> characters) {
    if (characters.isEmpty()) {
      return new LinuxTerminalRenderCommandBuilder().build();
    }
    else {
      var sortedEntries = sortEntriesByLocation(characters);
      var commandBuilder = new LinuxTerminalRenderCommandBuilder();
      for (var entry : sortedEntries) {
        var location = entry.getKey();
        var terminalCharacter = entry.getValue();
        commandBuilder.moveCursor(TerminalCoordinates.of(location.getRow(), location.getColumn()));
        commandBuilder.printCharacter(terminalCharacter);
      }
      return commandBuilder.build();
    }
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
