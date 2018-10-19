package com.wensby.terminablo.userinterface.terminal;

public class LinuxClearScreenRenderCommand implements ClearScreenRenderCommand {

  @Override
  public String toRenderString() {
    char escapeCode = 0x1B;
    return String.format("%c[2J", escapeCode);
  }
}
