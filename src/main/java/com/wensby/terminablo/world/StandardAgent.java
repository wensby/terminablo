package com.wensby.terminablo.world;

public class StandardAgent implements Agent {

  private AgentStats stats;
  private final String name;

  public StandardAgent(String name) {
    this.name = name;
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
}
