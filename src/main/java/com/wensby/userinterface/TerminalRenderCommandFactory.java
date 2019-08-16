package com.wensby.userinterface;

import java.util.List;

public interface TerminalRenderCommandFactory {

  TerminalRenderCommand createCommand(List<PositionedTerminalCharacter> characters);

  TerminalRenderCommand createMoveCursorCommand(int row, int column);

  TerminalRenderCommand createClearScreenCommand();

  TerminalRenderCommand createHideCursorCommand();

  TerminalRenderCommand createShowCursorCommand();
}
