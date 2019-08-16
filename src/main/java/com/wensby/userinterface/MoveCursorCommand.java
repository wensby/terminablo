package com.wensby.userinterface;

public interface MoveCursorCommand extends TerminalRenderCommand {

  TerminalCoordinates getCoordinates();
}
