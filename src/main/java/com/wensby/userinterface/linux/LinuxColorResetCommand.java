package com.wensby.userinterface.linux;

import com.wensby.userinterface.TerminalRenderCommand;

public class LinuxColorResetCommand implements TerminalRenderCommand {

  @Override
  public String toRenderString() {
    return (char) 0x1B + "[0m";
  }
}
