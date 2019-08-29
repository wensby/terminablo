package com.wensby.application.userinterface;

public interface TerminalCanvas {

  TerminalFrame createFrame();

  CanvasRenderResult renderFrame(TerminalFrame frame);
}
