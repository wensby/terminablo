package com.wensby.application.userinterface;

import java.awt.*;

public class LinuxColorAdjustmentCommand implements TerminalRenderCommand {

  private final String renderString;

  public LinuxColorAdjustmentCommand(Color background, Color foreground, boolean bold) {
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
    if (foregroundColorCode == 1) {
      colorAdjust.append(";39");
    }
    if (bold) {
      colorAdjust.append(";1");
    }
    else {
      colorAdjust.append(";22");
    }
    colorAdjust.append("m");
    this.renderString = colorAdjust.toString();
  }

  public LinuxColorAdjustmentCommand(CharacterDecoration decoration) {
    this(decoration.getBackgroundColor().orElse(null), decoration.getForegroundColor().orElse(null), decoration.isBold());
  }

  @Override
  public String toRenderString() {
    return renderString;
  }
}
