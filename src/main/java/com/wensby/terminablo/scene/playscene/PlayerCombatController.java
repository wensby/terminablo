package com.wensby.terminablo.scene.playscene;

import com.wensby.application.userinterface.Key;
import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.level.AgentPresence;
import com.wensby.terminablo.world.level.LevelLocation;
import com.wensby.application.userinterface.UserInput;

import java.time.Duration;
import java.util.Comparator;
import java.util.Optional;

public class PlayerCombatController implements Controller {

  private final Agent hero;
  private final PlaySceneModel model;

  public PlayerCombatController(Agent hero, PlaySceneModel model) {
    this.hero = hero;
    this.model = model;
  }

  @Override
  public void update(Duration elapsedTime, UserInput input) {
    if (input.getKeyStrokes().contains(Key.SPACE)) {
      model.getPlayerLocation()
          .map(this::nearestHostilePresence)
          .filter(Optional::isPresent)
          .map(Optional::get)
          .ifPresent(agentPresence -> {
            model.setCurrentTarget(agentPresence.getAgent());
            model.getLevel().putEntity(model.getLevel().locationOf(agentPresence).get(), new DamageEntity());
          });
    }
  }

  private Optional<AgentPresence> nearestHostilePresence(LevelLocation location) {
    return model.getMonsterPresences().stream()
        .filter(agent -> model.getLevel().locationOf(agent).isPresent())
        .min(Comparator.comparingInt(a -> getAgentDistance(location, a)));
  }

  private int getAgentDistance(LevelLocation location, AgentPresence a) {
    var levelLocation = model.getLevel().locationOf(a);
    return getDistance(location, levelLocation.get());
  }

  private Optional<LevelLocation> nearestMonsterLocation(LevelLocation location) {
    return model.getMonsterPresences().stream()
        .map(model.getLevel()::locationOf)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .min(Comparator.comparingInt(a -> getDistance(location, a)));
  }

  private int getDistance(LevelLocation a, LevelLocation b) {
    return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
  }
}
