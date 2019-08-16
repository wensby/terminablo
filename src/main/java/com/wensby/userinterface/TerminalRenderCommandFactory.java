package com.wensby.userinterface;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;

import java.util.Map;

public interface TerminalRenderCommandFactory {

  TerminalRenderCommand createCommand(Map<InterfaceLocation, TerminalCharacter> characters);

  TerminalRenderCommand createMoveCursorCommand(int row, int column);

  TerminalRenderCommand createClearScreenCommand();

  TerminalRenderCommand createHideCursorCommand();

  TerminalRenderCommand createShowCursorCommand();
}
