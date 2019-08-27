package com.wensby.application.userinterface;

public interface MoveCursorCommand extends TerminalRenderCommand {

  TerminalCoordinates getCoordinates();
}
