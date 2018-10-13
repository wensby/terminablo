package com.wensby.terminablo.world;

import com.wensby.terminablo.world.level.LevelEntityImpl;

public class AgentBuilder {

  public Agent build() {
    return new StandardAgent(new LevelEntityImpl());
  }
}
