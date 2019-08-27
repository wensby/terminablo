package com.wensby.terminablo.world.level;

import com.wensby.application.userinterface.TerminalCharacter;

import java.util.Optional;

public class LevelEntityImpl implements LevelEntity {

  private final TerminalCharacter character;

  public LevelEntityImpl(TerminalCharacter character) {
    this.character = character;
  }

  @Override
  public boolean isPassable() {
    return false;
  }

  @Override
  public Optional<TerminalCharacter> getCharacter() {
    return Optional.of(character);
  }
}
