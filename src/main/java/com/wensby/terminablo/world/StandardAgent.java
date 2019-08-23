package com.wensby.terminablo.world;

import com.wensby.terminablo.world.level.LevelEntity;

public class StandardAgent implements Agent {

  private AgentStats stats;
  private final String name;
  private LevelEntity levelEntity;

  public StandardAgent(String name, LevelEntity levelEntity) {
    this.name = name;
    this.levelEntity = levelEntity;
    stats = new AgentStats();
  }

  @Override
  public String getName() {
    return name;
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
