package com.wensby.terminablo.userinterface.terminal;

import com.wensby.userinterface.TerminalCharacter;

public interface TerminalRenderCommandFactory {

    TerminalRenderCommand createCommand(TerminalCharacter[][] characters);

    TerminalRenderCommand createMoveCursorCommand(int row, int column);
}
