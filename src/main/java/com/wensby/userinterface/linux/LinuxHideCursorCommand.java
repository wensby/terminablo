package com.wensby.userinterface.linux;

import com.wensby.userinterface.TerminalRenderCommand;

public class LinuxHideCursorCommand implements TerminalRenderCommand {

  @Override
  public String toRenderString() {
    char escapeCode = 0x1B;
    return String.format("%c[?25l", escapeCode);
  }
}
