package com.wensby.terminablo.world;

import com.wensby.terminablo.world.level.LevelEntity;
import com.wensby.terminablo.world.level.LevelEntityImpl;

public class AgentBuilder {

  private LevelEntity levelEntity = new LevelEntityImpl(".");

  public AgentBuilder withLevelEntity(LevelEntity entity) {
    this.levelEntity = entity;
    return this;
  }

  public Agent build() {
    return new StandardAgent(levelEntity);
  }
}
