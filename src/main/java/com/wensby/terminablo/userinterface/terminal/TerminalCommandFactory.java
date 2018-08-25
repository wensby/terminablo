package com.wensby.terminablo.userinterface.terminal;

public interface TerminalCommandFactory {

    TerminalRenderCommand createMoveCursorCommand(int row, int column);
}
