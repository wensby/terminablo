package com.wensby.userinterface.linux;

import com.wensby.userinterface.Ansi;
import com.wensby.userinterface.CharacterDecoration;
import com.wensby.userinterface.TerminalRenderCommand;

import java.awt.*;

public class LinuxColorAdjustmentCommand implements TerminalRenderCommand {

  private final String renderString;

  public LinuxColorAdjustmentCommand(Color background, Color foreground) {
    int backgroundColorCode = LinuxColorConverter.getBackgroundColorCode(background);
    int foregroundColorCode = LinuxColorConverter.getForegroundColorCode(foreground);
    StringBuilder colorAdjust = new StringBuilder();
    colorAdjust.append(Ansi.ESCAPE)
        .append("[")
        .append(foregroundColorCode)
        .append(";")
        .append(backgroundColorCode);
    if (backgroundColorCode == 1) {
      colorAdjust.append(";49");
    }
    colorAdjust.append("m");
    this.renderString = colorAdjust.toString();
  }

  public LinuxColorAdjustmentCommand(CharacterDecoration decoration) {
    this(decoration.getBackgroundColor().orElse(null), decoration.getForegroundColor().orElse(null));
  }

  @Override
  public String toRenderString() {
    return renderString;
  }
}
