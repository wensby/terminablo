package com.wensby.terminablo.scene.playscene;

import com.wensby.terminablo.userinterface.Key;
import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.level.LevelLocation;
import com.wensby.userinterface.UserInput;

import java.time.Duration;
import java.util.Comparator;
import java.util.Optional;

public class PlayerCombatController implements Controller {

  private final Agent hero;
  private final LevelSceneModel model;

  public PlayerCombatController(Agent hero, LevelSceneModel model) {
    this.hero = hero;
    this.model = model;
  }

  @Override
  public void update(Duration elapsedTime, UserInput input) {
    if (input.getKeyStrokes().contains(Key.SPACE)) {
      model.getLevel().locationOf(hero.getLevelEntity())
          .map(this::nearestMonsterLocation)
          .filter(Optional::isPresent)
          .map(Optional::get)
          .ifPresent(levelLocation -> model.getLevel().putEntity(levelLocation, new DamageEntity()));
    }
  }

  private Optional<LevelLocation> nearestMonsterLocation(LevelLocation location) {
    return model.getMonsters().stream()
        .map(Agent::getLevelEntity)
        .map(model.getLevel()::locationOf)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .min(Comparator.comparingInt(a -> getDistance(location, a)));
  }

  private int getDistance(LevelLocation a, LevelLocation b) {
    return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
  }
}
