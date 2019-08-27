package com.wensby.terminablo.world.level;

import com.wensby.application.userinterface.ComplexTerminalCharacterImpl;

public class LevelEntityFactory {

  public LevelEntity createWall() {
    return new LevelEntityImpl(new ComplexTerminalCharacterImpl("\uD83C\uDF33"));
  }
}
