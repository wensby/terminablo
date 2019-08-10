package com.wensby.terminablo;

import static java.awt.Color.GREEN;

import com.wensby.terminablo.world.level.LevelEntity;
import com.wensby.userinterface.TerminalCharacter;
import com.wensby.userinterface.TerminalCharacterFactory;
import java.awt.Color;

public class LevelEntityRenderer {

  private final TerminalCharacterFactory terminalCharacterFactory;

  public LevelEntityRenderer(
      TerminalCharacterFactory terminalCharacterFactory) {
    this.terminalCharacterFactory = terminalCharacterFactory;
  }

  public TerminalCharacter getTerminalCharacter(LevelEntity levelEntity) {
    return terminalCharacterFactory.createCharacter(levelEntity.getCharacter());
  }
}
