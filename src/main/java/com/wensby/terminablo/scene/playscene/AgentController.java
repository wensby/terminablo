package com.wensby.terminablo.scene.playscene;

import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.level.Level;
import com.wensby.terminablo.world.level.LevelLocation;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class AgentController {

  private Level level;
  private final Map<Agent, Instant> nextUpdateByAgent = new HashMap<>();

  public AgentController(Level level) {
    this.level = level;
  }

  public void update(Agent agent, Duration elapsedTime) {
    var now = Instant.now();
    if (now.isAfter(nextUpdateByAgent.computeIfAbsent(agent, a -> now))) {
      var agentLevelEntity = agent.getLevelEntity();
      Optional<LevelLocation> location = level.locationOf(agentLevelEntity);
      if (location.isPresent()) {
        level.removeEntity(agentLevelEntity);
        level.putEntity(location.get().plus(getMoveDiff()), agentLevelEntity);
        nextUpdateByAgent.put(agent, now.plusMillis(1000 + new Random().nextInt(1000)));
      }
    }
  }

  private LevelLocation getMoveDiff() {
    int i = new Random().nextInt(4);
    switch (i) {
      case 0: return LevelLocation.of(1, 0);
      case 1: return LevelLocation.of(0, 1);
      case 2: return LevelLocation.of(-1, 0);
      case 3: return LevelLocation.of(0, -1);
      default: return LevelLocation.of(0, 0);
    }
  }
}
