package com.wensby.terminablo.scene.playscene;

import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.level.Level;
import com.wensby.terminablo.world.level.LevelEntity;
import com.wensby.terminablo.world.level.LevelLocation;
import com.wensby.userinterface.UserInput;

import java.time.Duration;
import java.util.Optional;

import static com.wensby.terminablo.userinterface.Key.*;
import static com.wensby.terminablo.userinterface.Key.ARROW_RIGHT;

public class PlayerController implements Controller {

  private final Agent hero;
  private final Level level;

  public PlayerController(Agent hero, Level level) {
    this.hero = hero;
    this.level = level;
  }

  @Override
  public void update(Duration elapsedTime, UserInput input) {

  }



  public void updateCharacterMovement(Duration elapsedTime, UserInput input) {
    final LevelEntity heroEntity = hero.getLevelEntity();
    var newLocation = getNextLocation(input);
    if (level.entities(newLocation).size() == 0) {
      level.removeEntity(heroEntity);
      level.putEntity(newLocation, heroEntity);
    }
  }

  private LevelLocation getNextLocation(UserInput input) {
    LevelLocation location = level.locationOf(hero.getLevelEntity()).get();
    if (input.getKeyStrokes().contains(ARROW_UP)) {
      location = location.plus(LevelLocation.of(0, -1));
    }
    if (input.getKeyStrokes().contains(ARROW_DOWN)) {
      location = location.plus(LevelLocation.of(0, 1));
    }
    if (input.getKeyStrokes().contains(ARROW_LEFT)) {
      location = location.plus(LevelLocation.of(-1, 0));
    }
    if (input.getKeyStrokes().contains(ARROW_RIGHT)) {
      location = location.plus(LevelLocation.of(1, 0));
    }
    return location;
  }
}
