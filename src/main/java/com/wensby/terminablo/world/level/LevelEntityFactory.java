package com.wensby.terminablo.world.level;

public class LevelEntityFactory {

  public LevelEntity createWall() {
    return new LevelEntityImpl("\uD83C\uDF33");
  }
}
