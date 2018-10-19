package com.wensby.terminablo.userinterface.terminal;

public class LinuxShowCursorCommand implements TerminalRenderCommand {

  @Override
  public String toRenderString() {
    char escapeCode = 0x1B;
    return String.format("%c[?25h", escapeCode);
  }
}
