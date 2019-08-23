package com.wensby.terminablo.player;

import com.wensby.terminablo.world.AgentStats;

public class PlayerStats {

  private int level;
  private double experience;
  private AgentStats agentStats;

  public PlayerStats(int level, double experience, AgentStats agentStats) {
    this.level = level;
    this.experience = experience;
    this.agentStats = agentStats;
  }
}
