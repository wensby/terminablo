package com.wensby.terminablo.scene.playscene;

import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.level.*;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static java.util.stream.Collectors.toMap;

public class AgentController {

  private Level level;
  private final Map<Agent, AgentPresence> presenceByAgent;
  private final Map<Agent, Instant> nextUpdateByAgent = new HashMap<>();

  public AgentController(Level level, Set<AgentPresence> agentPresences) {
    this.level = level;
    presenceByAgent = agentPresences.stream()
        .collect(toMap(AgentPresence::getAgent, presence -> presence));
  }

  public void update(Agent agent, Duration elapsedTime) {
    var now = Instant.now();
    if (now.isAfter(nextUpdateByAgent.computeIfAbsent(agent, a -> now))) {
      var agentLevelEntity = presenceByAgent.get(agent);
      level.locationOf(agentLevelEntity).ifPresent(location -> move(agent, now, agentLevelEntity, location));
    }
  }

  private void move(Agent agent, Instant now, AgentPresence agentLevelEntity, LevelLocation location) {
    level.removeEntity(location, agentLevelEntity);
    level.putEntity(location.plus(getMoveDiff()), agentLevelEntity);
    nextUpdateByAgent.put(agent, now.plusMillis(1000 + new Random().nextInt(1000)));
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
