package com.wensby.application.userinterface;

public interface TerminalCommandFactory {

  TerminalRenderCommand createMoveCursorCommand(int row, int column);
}
