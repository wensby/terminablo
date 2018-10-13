package com.wensby.terminablo.userinterface.terminal;

public interface MoveCursorCommand extends TerminalRenderCommand {

  TerminalCoordinates getCoordinates();
}
