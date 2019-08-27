package com.wensby.application.userinterface;

public class LinuxShowCursorCommand implements TerminalRenderCommand {

  @Override
  public String toRenderString() {
    return String.format("%c[?25h", Ansi.ESCAPE);
  }
}
