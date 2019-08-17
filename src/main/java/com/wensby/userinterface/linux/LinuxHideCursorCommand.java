package com.wensby.userinterface.linux;

import com.wensby.userinterface.Ansi;
import com.wensby.userinterface.TerminalRenderCommand;

public class LinuxHideCursorCommand implements TerminalRenderCommand {

  @Override
  public String toRenderString() {
    return String.format("%c[?25l", Ansi.ESCAPE);
  }
}
