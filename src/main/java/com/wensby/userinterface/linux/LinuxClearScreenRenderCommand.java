package com.wensby.userinterface.linux;

import com.wensby.userinterface.Ansi;
import com.wensby.userinterface.ClearScreenRenderCommand;

public class LinuxClearScreenRenderCommand implements ClearScreenRenderCommand {

  @Override
  public String toRenderString() {
    return String.format("%c[2J", Ansi.ESCAPE);
  }
}
