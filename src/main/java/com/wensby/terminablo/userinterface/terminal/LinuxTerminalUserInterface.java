package com.wensby.terminablo.userinterface.terminal;

import com.wensby.terminablo.userinterface.Key;
import com.wensby.terminablo.userinterface.UserInterface;
import com.wensby.terminablo.userinterface.VisualCanvas;
import com.wensby.userinterface.UserInput;
import java.util.List;

public class LinuxTerminalUserInterface implements UserInterface {

  private final LinuxTerminal linuxTerminal;
  private final LinuxTerminalKeyboard keyboard;
  private final LinuxTerminalVisualCanvas canvas;

  public LinuxTerminalUserInterface(LinuxTerminal linuxTerminal, LinuxTerminalKeyboard keyboard,
      LinuxTerminalVisualCanvas canvas) {
    this.linuxTerminal = linuxTerminal;
    this.keyboard = keyboard;
    this.canvas = canvas;
  }

  private void clearCanvas(char[][] canvas) {
    for (int y = 0; y < canvas.length; y++) {
      char[] line = canvas[y];
      for (int x = 0; x < line.length; x++) {
        line[x] = ' ';
      }
    }
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

  @Override
  public void release() {
    linuxTerminal.setTerminalCooked();
    System.out.println();
  }
}
