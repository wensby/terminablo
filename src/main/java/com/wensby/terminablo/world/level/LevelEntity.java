package com.wensby.terminablo.world.level;

import com.wensby.userinterface.TerminalCharacter;

import java.util.Optional;

public interface LevelEntity {

  boolean isPassable();

  Optional<TerminalCharacter> getCharacter();
}
