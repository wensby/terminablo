package com.wensby.terminablo.scene.levelscene;

import static com.wensby.terminablo.userinterface.Key.ARROW_DOWN;
import static com.wensby.terminablo.userinterface.Key.ARROW_LEFT;
import static com.wensby.terminablo.userinterface.Key.ARROW_RIGHT;
import static com.wensby.terminablo.userinterface.Key.ARROW_UP;
import static com.wensby.terminablo.userinterface.Key.ESCAPE;

import com.wensby.terminablo.scene.SceneStack;
import com.wensby.terminablo.world.level.LevelEntity;
import com.wensby.terminablo.world.level.LevelLocation;
import com.wensby.userinterface.UserInput;
import java.math.BigDecimal;
import java.time.Duration;

public class LevelSceneController {

  private final SceneStack sceneStack;
  private final LevelSceneModel model;

  public LevelSceneController(SceneStack sceneStack,
      LevelSceneModel model) {
    this.sceneStack = sceneStack;
    this.model = model;
  }

  public void update(Duration elapsedTime, UserInput input) {
    BigDecimal plus = model.getHero().getStats().getLife().add(BigDecimal.TEN);
    model.getHero().getStats().setLife(plus);
    if (input.getKeyStrokes().contains(ESCAPE)) {
      sceneStack.pop();
    }
    updateCharacterMovement(elapsedTime, input);
  }

  private void updateCharacterMovement(Duration elapsedTime, UserInput input) {
    final LevelEntity heroEntity = model.getHero().getLevelEntity();
    LevelLocation location = model.getLevel().removeEntity(heroEntity).get();
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
    model.getLevel().putEntity(location, heroEntity);
  }
}
