package com.wensby.application.userinterface;

public class LinuxClearScreenRenderCommand implements ClearScreenRenderCommand {

  @Override
  public String toRenderString() {
    return String.format("%c[2J", Ansi.ESCAPE);
  }
}
