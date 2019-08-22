package com.wensby.userinterface.linux;

import com.wensby.terminablo.userinterface.UserInterfaceFactory;
import com.wensby.userinterface.CharacterDifferenceFactory;

import java.io.InputStream;

public class LinuxTerminalUserInterfaceFactory implements UserInterfaceFactory {

  private static LinuxTerminalKeyboard terminalKeyboard;
  private final LinuxTerminal linuxTerminal;
  private final LinuxTerminalRenderCommandFactory commandFactory;
  private CharacterDifferenceFactory characterDifferenceFactory;

  public LinuxTerminalUserInterfaceFactory(LinuxTerminal linuxTerminal,
      final LinuxTerminalRenderCommandFactory commandFactory, CharacterDifferenceFactory characterDifferenceFactory) {
    this.linuxTerminal = linuxTerminal;
    this.commandFactory = commandFactory;
    this.characterDifferenceFactory = characterDifferenceFactory;
  }

  @Override
  public LinuxTerminalUserInterface createUserInterface() {
    linuxTerminal.setTerminalRaw();
    linuxTerminal.showCursor(false);
    if (terminalKeyboard == null) {
      terminalKeyboard = createLinuxTerminalKeyboard(linuxTerminal.getInputStream());
    }
    LinuxTerminalFrameFactory frameFactory = new LinuxTerminalFrameFactory(linuxTerminal);
    LinuxTerminalVisualCanvas canvas = new LinuxTerminalVisualCanvas(frameFactory, linuxTerminal.getOutputStream(),
        commandFactory, characterDifferenceFactory);
    return new LinuxTerminalUserInterface(terminalKeyboard, canvas);
  }

  private LinuxTerminalKeyboard createLinuxTerminalKeyboard(InputStream in) {
    return new LinuxTerminalKeyboard(in);
  }
}
