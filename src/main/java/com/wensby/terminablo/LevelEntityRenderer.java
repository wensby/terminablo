package com.wensby.terminablo;

import com.wensby.terminablo.world.level.LevelEntity;
import com.wensby.userinterface.TerminalCharacter;
import com.wensby.userinterface.TerminalCharacterFactory;

public class LevelEntityRenderer {

  private final TerminalCharacterFactory terminalCharacterFactory;

  public LevelEntityRenderer(
      TerminalCharacterFactory terminalCharacterFactory) {
    this.terminalCharacterFactory = terminalCharacterFactory;
  }

  public TerminalCharacter getTerminalCharacter(LevelEntity levelEntity) {
    return terminalCharacterFactory.createCharacter('#');
  }
}
