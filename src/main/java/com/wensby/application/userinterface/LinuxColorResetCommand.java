package com.wensby.application.userinterface;

public class LinuxColorResetCommand implements TerminalRenderCommand {

  @Override
  public String toRenderString() {
    return Ansi.ESCAPE + "[0m";
  }
}
