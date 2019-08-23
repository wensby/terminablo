package com.wensby.terminablo.world;

import com.wensby.terminablo.world.level.LevelEntity;

public interface Agent {

  String getName();

  AgentStats getStats();

  LevelEntity getLevelEntity();
}
