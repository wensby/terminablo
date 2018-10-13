package com.wensby.terminablo.world;

import com.wensby.terminablo.world.level.LevelEntity;

public class StandardAgent implements Agent {

  private AgentStats stats;
  private LevelEntity levelEntity;

  public StandardAgent(LevelEntity levelEntity) {
    this.levelEntity = levelEntity;
    stats = new AgentStats();
  }

  @Override
  public AgentStats getStats() {
    return stats;
  }

  @Override
  public LevelEntity getLevelEntity() {
    return levelEntity;
  }
}
