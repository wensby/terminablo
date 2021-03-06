package com.wensby.application.userinterface;

import java.awt.*;
import java.util.Objects;
import java.util.Optional;

public class CharacterDecoration {

  private final boolean bold;
  private final Color backgroundColor;
  private final Color foregroundColor;

  public CharacterDecoration(Color backgroundColor, Color foregroundColor, boolean bold) {
    this.backgroundColor = backgroundColor;
    this.foregroundColor = foregroundColor;
    this.bold = bold;
  }

  public Optional<Color> getBackgroundColor() {
    return Optional.ofNullable(backgroundColor);
  }

  public Optional<Color> getForegroundColor() {
    return Optional.ofNullable(foregroundColor);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CharacterDecoration that = (CharacterDecoration) o;
    return bold == that.bold &&
        Objects.equals(backgroundColor, that.backgroundColor) &&
        Objects.equals(foregroundColor, that.foregroundColor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bold, backgroundColor, foregroundColor);
  }

  public boolean isBold() {
    return bold;
  }
}
