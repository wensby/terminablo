package com.wensby.terminablo.world;

import com.wensby.terminablo.world.level.LevelEntity;

public interface Agent {

  AgentStats getStats();

  LevelEntity getLevelEntity();
}
