package com.wensby.application.userinterface;


import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class LinuxColorAdjustmentCommand implements TerminalRenderCommand {

  private final String renderString;

  public LinuxColorAdjustmentCommand(Color background, Color foreground, boolean bold) {
    var backgroundColorCode = LinuxColorConverter.getBackgroundColorCode(background);
    var foregroundColorCode = LinuxColorConverter.getForegroundColorCode(foreground);
    StringBuilder colorAdjust = new StringBuilder();
    colorAdjust.append(Ansi.ESCAPE)
        .append("[")
        .append(foregroundColorCode.stream().map(String::valueOf).collect(joining(";")))
        .append(";")
        .append(backgroundColorCode.stream().map(String::valueOf).collect(joining(";")));
    if (backgroundColorCode.equals(List.of(1))) {
      colorAdjust.append(";49");
    }
    if (foregroundColorCode.equals(List.of(1))) {
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
