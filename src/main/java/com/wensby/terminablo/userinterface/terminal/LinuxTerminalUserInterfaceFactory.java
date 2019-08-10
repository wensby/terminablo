package com.wensby.terminablo.userinterface.terminal;

import com.wensby.terminablo.userinterface.UserInterfaceFactory;

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
      terminalKeyboard = createLinuxTerminalKeyboard();
    }
    LinuxTerminalFrameFactory frameFactory = new LinuxTerminalFrameFactory(linuxTerminal);
    LinuxTerminalVisualCanvas canvas = new LinuxTerminalVisualCanvas(frameFactory, System.out,
        commandFactory, characterDifferenceFactory);
    return new LinuxTerminalUserInterface(linuxTerminal, terminalKeyboard, canvas);
  }

  private LinuxTerminalKeyboard createLinuxTerminalKeyboard() {
    return new LinuxTerminalKeyboard(System.in);
  }
}
