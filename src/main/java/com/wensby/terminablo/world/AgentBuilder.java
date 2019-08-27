package com.wensby.terminablo.world;

import com.wensby.terminablo.world.level.LevelEntity;
import com.wensby.terminablo.world.level.LevelEntityImpl;
import com.wensby.application.userinterface.SimpleTerminalCharacterImpl;

public class AgentBuilder {

  private LevelEntity levelEntity = new LevelEntityImpl(new SimpleTerminalCharacterImpl('.'));
  private String name;

  public AgentBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public AgentBuilder withLevelEntity(LevelEntity entity) {
    this.levelEntity = entity;
    return this;
  }

  public Agent build() {
    return new StandardAgent(name, levelEntity);
  }
}
