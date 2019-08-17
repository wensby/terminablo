package com.wensby.terminablo;

import com.wensby.terminablo.world.level.LevelEntity;
import com.wensby.userinterface.TerminalCharacter;

public class LevelEntityRenderer {

  public TerminalCharacter getTerminalCharacter(LevelEntity levelEntity) {
    return levelEntity.getCharacter();
  }
}
