package com.wensby.userinterface;

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
}
