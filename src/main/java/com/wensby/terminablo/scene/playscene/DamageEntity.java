package com.wensby.terminablo.scene.playscene;

import com.wensby.terminablo.world.level.LevelEntity;
import com.wensby.application.userinterface.SimpleTerminalCharacterImpl;
import com.wensby.application.userinterface.TerminalCharacter;

import java.util.Optional;

public class DamageEntity implements LevelEntity {

  @Override
  public boolean isPassable() {
    return true;
  }

  @Override
  public Optional<TerminalCharacter> getCharacter() {
    return Optional.of(new SimpleTerminalCharacterImpl('x'));
  }
}
