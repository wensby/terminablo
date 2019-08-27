package com.wensby.application.userinterface;

public class LinuxHideCursorCommand implements TerminalRenderCommand {

  @Override
  public String toRenderString() {
    return String.format("%c[?25l", Ansi.ESCAPE);
  }
}
