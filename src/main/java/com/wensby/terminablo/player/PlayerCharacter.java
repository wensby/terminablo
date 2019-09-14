package com.wensby.terminablo.player;

import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.AgentStats;

public class PlayerCharacter implements Agent {

  private final String name;
  private final AgentStats agentStats;

  public PlayerCharacter(String name, AgentStats agentStats) {
    this.name = name;
    this.agentStats = agentStats;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public AgentStats getStats() {
    return agentStats;
  }

  public CharacterClass getCharacterClass() {
    return new CharacterClass();
  }

  public int getLevel() {
    return 1;
  }
}
