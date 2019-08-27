package com.wensby.terminablo.world.level;

import com.wensby.application.userinterface.TerminalCharacter;

import java.util.Optional;

public interface LevelEntity {

  boolean isPassable();

  Optional<TerminalCharacter> getCharacter();
}
