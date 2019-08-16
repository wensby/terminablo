package com.wensby.userinterface;

import java.util.Objects;

public class SimpleTerminalCharacterImpl implements SimpleTerminalCharacter {

  private final char character;

  public SimpleTerminalCharacterImpl(char character) {
    this.character = character;
  }

  @Override
  public char getCharacter() {
    return character;
  }

  @Override
  public String toString() {
    return "SimpleTerminalCharacter{" +
        "character=" + character +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return character == ((SimpleTerminalCharacterImpl) o).character;
  }

  @Override
  public int hashCode() {
    return Objects.hash(character);
  }
}
