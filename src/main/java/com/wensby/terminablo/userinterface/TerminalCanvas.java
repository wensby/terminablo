package com.wensby.terminablo.userinterface;

import com.wensby.userinterface.TerminalFrame;

public interface TerminalCanvas {

  TerminalFrame createFrame();

  void renderFrame(TerminalFrame frame);
}
