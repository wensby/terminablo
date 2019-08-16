package com.wensby.userinterface;

public interface TerminalCommandFactory {

  TerminalRenderCommand createMoveCursorCommand(int row, int column);
}
