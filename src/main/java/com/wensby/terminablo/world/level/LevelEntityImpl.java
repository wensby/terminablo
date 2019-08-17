package com.wensby.terminablo.world.level;

import com.wensby.userinterface.TerminalCharacter;

public class LevelEntityImpl implements LevelEntity {

  private final TerminalCharacter character;

  public LevelEntityImpl(TerminalCharacter character) {
    this.character = character;
  }

  @Override
  public TerminalCharacter getCharacter() {
    return character;
  }
}
