package com.wensby.userinterface.linux;

import com.wensby.userinterface.Ansi;
import com.wensby.userinterface.TerminalRenderCommand;

public class LinuxColorResetCommand implements TerminalRenderCommand {

  @Override
  public String toRenderString() {
    return Ansi.ESCAPE + "[0m";
  }
}
