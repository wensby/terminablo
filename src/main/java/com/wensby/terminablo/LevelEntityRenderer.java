package com.wensby.terminablo;

import com.wensby.terminablo.world.level.LevelEntity;
import com.wensby.application.userinterface.TerminalCharacter;

import java.util.Optional;

public class LevelEntityRenderer {

  public Optional<TerminalCharacter> getTerminalCharacter(LevelEntity levelEntity) {
    return levelEntity.getCharacter();
  }
}
