package com.wensby.application.userinterface;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;

import java.util.Objects;

public class PositionedTerminalCharacter {

  private final InterfaceLocation location;
  private final TerminalCharacter character;

  public PositionedTerminalCharacter(InterfaceLocation location, TerminalCharacter character) {
    this.location = Objects.requireNonNull(location);
    this.character = Objects.requireNonNull(character);
  }

  public InterfaceLocation getLocation() {
    return location;
  }

  public TerminalCharacter getCharacter() {
    return character;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PositionedTerminalCharacter that = (PositionedTerminalCharacter) o;
    return Objects.equals(location, that.location) &&
        Objects.equals(character, that.character);
  }

  @Override
  public int hashCode() {
    return Objects.hash(location, character);
  }
}
