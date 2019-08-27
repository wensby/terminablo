package com.wensby.application.userinterface;

import com.wensby.terminablo.userinterface.Keyboard;
import com.wensby.terminablo.userinterface.TerminalCanvas;

public class UserInterfaceFactory {

  private Keyboard terminalKeyboard;
  private TerminalCanvas canvas;

  public UserInterfaceFactory(TerminalCanvas terminalVisualCanvas, Keyboard terminalKeyboard) {
    this.canvas = terminalVisualCanvas;
    this.terminalKeyboard = terminalKeyboard;
  }

}
