package com.wensby.terminablo.scene.levelscene;

import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.level.Level;

public class LevelSceneModel {

  private final Agent hero;
  private final Level level;

  public LevelSceneModel(Agent hero, Level level) {
    this.hero = hero;
    this.level = level;
  }

  public Agent getHero() {
    return hero;
  }

  public Level getLevel() {
    return level;
  }
}
