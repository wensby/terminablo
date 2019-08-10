package com.wensby.terminablo.userinterface.terminal;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.userinterface.TerminalCharacter;

import java.util.Map;

public interface TerminalRenderCommandFactory {

  TerminalRenderCommand createCommand(TerminalCharacter[][] characters);

  TerminalRenderCommand createCommand(Map<InterfaceLocation, ? extends TerminalCharacter> characters);

  TerminalRenderCommand createMoveCursorCommand(int row, int column);

  TerminalRenderCommand createClearScreenCommand();

  TerminalRenderCommand createHideCursorCommand();

  TerminalRenderCommand createShowCursorCommand();
}
