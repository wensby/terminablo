package com.wensby.userinterface.linux;

import com.wensby.terminablo.userinterface.Key;
import com.wensby.terminablo.userinterface.Keyboard;
import com.wensby.terminablo.userinterface.TerminalCanvas;
import com.wensby.userinterface.UserInput;

import java.util.List;

public class TerminalUserInterface {

  private final Keyboard keyboard;
  private final TerminalCanvas canvas;

  public TerminalUserInterface(Keyboard keyboard, TerminalCanvas canvas) {
    this.keyboard = keyboard;
    this.canvas = canvas;
  }

  public UserInput pollUserInput() {
    List<Key> keyStrokes = keyboard.getKeyStrokes();
    return new UserInput(keyStrokes);
  }

  public TerminalCanvas getCanvas() {
    return canvas;
  }
}
