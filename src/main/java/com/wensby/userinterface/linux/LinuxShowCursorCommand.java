package com.wensby.userinterface.linux;

import com.wensby.userinterface.Ansi;
import com.wensby.userinterface.TerminalRenderCommand;

public class LinuxShowCursorCommand implements TerminalRenderCommand {

  @Override
  public String toRenderString() {
    return String.format("%c[?25h", Ansi.ESCAPE);
  }
}
