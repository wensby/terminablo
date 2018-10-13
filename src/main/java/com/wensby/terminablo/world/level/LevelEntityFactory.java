package com.wensby.terminablo.world.level;

public class LevelEntityFactory {

  public LevelEntity createWall() {
    return new LevelEntityImpl();
  }
}
