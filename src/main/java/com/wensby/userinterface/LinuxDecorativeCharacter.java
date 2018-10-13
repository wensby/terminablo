package com.wensby.userinterface;

import java.awt.Color;

public class LinuxDecorativeCharacter implements TerminalCharacter {

  private final CharSequence charSequence;
  private final Color foregroundColor;
  private final Color backgroundColor;

  LinuxDecorativeCharacter(CharSequence charSequence, Color foregroundColor,
      Color backgroundColor) {
    this.charSequence = charSequence;
    this.foregroundColor = foregroundColor;
    this.backgroundColor = backgroundColor;
  }

  LinuxDecorativeCharacter(CharSequence character) {
    this(character, null, null);
  }

  @Override
  public String toString() {
    return "LinuxDecorativeCharacter{" +
        "charSequence=" + charSequence +
        ", backgroundColor=" + backgroundColor +
        '}';
  }

  public CharSequence getCharSequence() {
    return charSequence;
  }

  public Color getBackgroundColor() {
    return backgroundColor;
  }

  @Override
  public String toRenderString() {
    int backgroundColorCode = LinuxColorConverter.getBackgroundColorCode(backgroundColor);
    int foregroundColorCode = LinuxColorConverter.getForegroundColorCode(foregroundColor);
    StringBuilder colorAdjust = new StringBuilder();
    colorAdjust.append((char) 0x1B)
        .append("[")
        .append(foregroundColorCode)
        .append(";")
        .append(backgroundColorCode);
    if (backgroundColorCode == 1) {
      colorAdjust.append(";49");
    }
    colorAdjust.append("m");
    String colorReset = (char) 0x1B + "[0m";
    return colorAdjust.toString() + charSequence + colorReset;
  }

  public Color getForegroundColor() {
    return foregroundColor;
  }

  @Override
  public boolean isLong() {
    return charSequence.length() > 1;
  }
}
