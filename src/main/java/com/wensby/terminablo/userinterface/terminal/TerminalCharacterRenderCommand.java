package com.wensby.terminablo.userinterface.terminal;

import com.wensby.userinterface.TerminalCharacter;

import java.util.Objects;

public class TerminalCharacterRenderCommand implements TerminalRenderCommand {

  private final TerminalCharacter character;

  public TerminalCharacterRenderCommand(TerminalCharacter character) {
    this.character = character;
  }

  @Override
  public String toString() {
    return "TerminalCharacterRenderCommand{" +
        "character=" + character +
        '}';
  }

  @Override
  public String toRenderString() {
    return character.toRenderString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TerminalCharacterRenderCommand that = (TerminalCharacterRenderCommand) o;
    return Objects.equals(character, that.character);
  }

  @Override
  public int hashCode() {
    return Objects.hash(character);
  }
}
