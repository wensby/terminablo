package com.wensby.userinterface;

import java.util.Objects;

public class SimpleTerminalCharacter implements TerminalCharacter {

  private final char character;

  public SimpleTerminalCharacter(char character) {
    this.character = character;
  }

  @Override
  public String toString() {
    return "SimpleTerminalCharacter{" +
        "character=" + character +
        '}';
  }

  @Override
  public String toRenderString() {
    return String.valueOf(character);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SimpleTerminalCharacter that = (SimpleTerminalCharacter) o;
    return character == that.character;
  }

  @Override
  public int hashCode() {
    return Objects.hash(character);
  }

  @Override
  public boolean isLong() {
    return false;
  }
}
