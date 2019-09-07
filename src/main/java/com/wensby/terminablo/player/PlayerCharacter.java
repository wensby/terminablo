package com.wensby.terminablo.player;

import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.AgentStats;
import com.wensby.terminablo.world.level.LevelEntity;

public class PlayerCharacter implements Agent {

  private final String name;
  private final AgentStats agentStats;
  private final LevelEntity levelEntity;

  public PlayerCharacter(String name, AgentStats agentStats, LevelEntity entity) {
    this.name = name;
    this.agentStats = agentStats;
    levelEntity = entity;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public AgentStats getStats() {
    return agentStats;
  }

  @Override
  public LevelEntity getLevelEntity() {
    return levelEntity;
  }

  public CharacterClass getCharacterClass() {
    return new CharacterClass();
  }

  public int getLevel() {
    return 1;
  }
}
