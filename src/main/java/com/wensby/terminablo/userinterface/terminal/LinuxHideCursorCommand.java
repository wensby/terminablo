package com.wensby.terminablo.userinterface.terminal;

public class LinuxHideCursorCommand implements TerminalRenderCommand {

  @Override
  public String toRenderString() {
    char escapeCode = 0x1B;
    return String.format("%c[?25l", escapeCode);
  }
}
