package com.wensby.terminablo.userinterface.terminal;

import com.wensby.userinterface.TerminalCharacter;

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
}
