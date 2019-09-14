package com.wensby.terminablo.world;

public class AgentBuilder {

  private String name;

  public AgentBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public Agent build() {
    return new StandardAgent(name);
  }
}
