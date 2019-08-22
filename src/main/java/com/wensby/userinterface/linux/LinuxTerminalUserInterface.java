package com.wensby.userinterface.linux;

import com.wensby.terminablo.userinterface.Key;
import com.wensby.terminablo.userinterface.UserInterface;
import com.wensby.terminablo.userinterface.VisualCanvas;
import com.wensby.userinterface.UserInput;

import java.util.List;

public class LinuxTerminalUserInterface implements UserInterface {

  private final LinuxTerminalKeyboard keyboard;
  private final LinuxTerminalVisualCanvas canvas;

  public LinuxTerminalUserInterface(LinuxTerminalKeyboard keyboard, LinuxTerminalVisualCanvas canvas) {
    this.keyboard = keyboard;
    this.canvas = canvas;
  }

  @Override
  public UserInput pollUserInput() {
    List<Key> keyStrokes = keyboard.getKeyStrokes();
    return new UserInput(keyStrokes);
  }

  @Override
  public VisualCanvas getCanvas() {
    return canvas;
  }
}
