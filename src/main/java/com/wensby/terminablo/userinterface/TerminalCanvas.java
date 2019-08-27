package com.wensby.terminablo.userinterface;

import com.wensby.application.userinterface.TerminalFrame;

public interface TerminalCanvas {

  TerminalFrame createFrame();

  void renderFrame(TerminalFrame frame);
}
