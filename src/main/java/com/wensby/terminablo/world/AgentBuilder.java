package com.wensby.terminablo.world;

import com.wensby.terminablo.world.level.LevelEntity;
import com.wensby.terminablo.world.level.LevelEntityImpl;
import com.wensby.userinterface.SimpleTerminalCharacterImpl;

public class AgentBuilder {

  private LevelEntity levelEntity = new LevelEntityImpl(new SimpleTerminalCharacterImpl('.'));

  public AgentBuilder withLevelEntity(LevelEntity entity) {
    this.levelEntity = entity;
    return this;
  }

  public Agent build() {
    return new StandardAgent(levelEntity);
  }
}
