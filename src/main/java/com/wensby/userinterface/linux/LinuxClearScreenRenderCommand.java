package com.wensby.userinterface.linux;

import com.wensby.userinterface.ClearScreenRenderCommand;

public class LinuxClearScreenRenderCommand implements ClearScreenRenderCommand {

  @Override
  public String toRenderString() {
    char escapeCode = 0x1B;
    return String.format("%c[2J", escapeCode);
  }
}
