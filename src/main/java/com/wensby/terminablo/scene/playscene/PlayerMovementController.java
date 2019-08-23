package com.wensby.terminablo.scene.playscene;

import com.wensby.terminablo.userinterface.Key;
import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.level.Level;
import com.wensby.terminablo.world.level.LevelEntity;
import com.wensby.terminablo.world.level.LevelLocation;
import com.wensby.userinterface.UserInput;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static com.wensby.terminablo.userinterface.Key.*;

public class PlayerMovementController implements Controller {

  private static final Set<Key> movementKeys = Set.of(ARROW_UP, ARROW_DOWN, ARROW_LEFT, ARROW_RIGHT);

  private static final AgentSpeed BASIC_WALKING_SPEED = AgentSpeed.yardsPerSecond(6);
  private static final AgentSpeed BASIC_RUNNING_SPEED = AgentSpeed.yardsPerSecond(9);
  private final Agent hero;
  private final Level level;

  private Duration movementElapsedTime = Duration.ZERO;
  private LevelLocation movementDelta = LevelLocation.ZERO;
  private boolean running;
  private AgentSpeed playerSpeed;

  public PlayerMovementController(Agent hero, Level level) {
    this.hero = hero;
    this.level = level;
  }

  @Override
  public void update(Duration elapsedTime, UserInput input) {
    updateMovementElapsedTime(elapsedTime);
    updatePlayerSpeed();
    var keyStrokes = input.getKeyStrokes();
    if (keyStrokes.contains(R_SMALL)) {
      toggleRunning();
    }
    updateMovementDelta(keyStrokes);
    var moved = resolveMovement(movementDelta, playerSpeed);
    if (moved) {
      if (!running) {
        movementDelta = LevelLocation.ZERO;
      }
    }
  }

  private boolean resolveMovement(LevelLocation delta, AgentSpeed speed) {
    var moved = false;
    var yards = yardsToCover(speed);
    for (int i = 0; i < (long) yards; i++) {
      if (!movementDelta.equals(LevelLocation.ZERO)) {
        moveOneYard(delta);
        moved = true;
      }
    }
    var msPerYard = speed.durationPerYard().toMillis();
    movementElapsedTime = Duration.ofMillis(movementElapsedTime.toMillis() % msPerYard);
    return moved;
  }

  private void updateMovementDelta(List<Key> keyStrokes) {
    if (movementKeys.stream().anyMatch(keyStrokes::contains)) {
      LevelLocation location = LevelLocation.ZERO;
      if (keyStrokes.contains(ARROW_UP)) {
        location = location.plus(LevelLocation.of(0, -1));
      }
      if (keyStrokes.contains(ARROW_DOWN)) {
        location = location.plus(LevelLocation.of(0, 1));
      }
      if (keyStrokes.contains(ARROW_LEFT)) {
        location = location.plus(LevelLocation.of(-1, 0));
      }
      if (keyStrokes.contains(ARROW_RIGHT)) {
        location = location.plus(LevelLocation.of(1, 0));
      }
      movementDelta = location;
    }
  }

  private void updateMovementElapsedTime(Duration elapsedTime) {
    movementElapsedTime = movementElapsedTime.plus(elapsedTime);
  }

  private void updatePlayerSpeed() {
    playerSpeed = running ? BASIC_RUNNING_SPEED : BASIC_WALKING_SPEED;
  }

  private void toggleRunning() {
    running = !running;
  }

  private float yardsToCover(AgentSpeed playerSpeed) {
    var capacity = (float) movementElapsedTime.toMillis() / (float) playerSpeed.durationPerYard().toMillis();
    if (running) {
      return capacity;
    }
    else {
      return capacity >= 1.f ? 1.f : capacity;
    }
  }

  private void moveOneYard(LevelLocation delta) {
    var heroLevelLocation = level.locationOf(hero.getLevelEntity());
    if (heroLevelLocation.isPresent()) {
      var newLocation = heroLevelLocation.get().plus(delta);
      if (level.entities(newLocation).stream().allMatch(LevelEntity::isPassable)) {
        var heroEntity = hero.getLevelEntity();
        level.removeEntity(heroEntity);
        level.putEntity(newLocation, heroEntity);
      }
    }
  }

}
