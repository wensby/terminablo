package com.wensby.terminablo.scene.playscene;

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

public class LevelSceneController implements Controller {

  private final SceneStack sceneStack;
  private final AgentController agentController;
  private final LevelSceneModel model;
  private final PlayerController playerController;

  public LevelSceneController(SceneStack sceneStack,
      AgentController agentController, LevelSceneModel model,
      PlayerController playerController) {
    this.sceneStack = sceneStack;
    this.agentController = agentController;
    this.playerController = playerController;
    this.model = model;
  }

  @Override
  public void update(Duration elapsedTime, UserInput input) {
    BigDecimal plus = model.getHero().getStats().getLife().add(BigDecimal.TEN);
    model.getHero().getStats().setLife(plus);
    if (input.getKeyStrokes().contains(ESCAPE)) {
      sceneStack.pop();
    }
    updateMonsters(elapsedTime);
    playerController.updateCharacterMovement(elapsedTime, input);
  }

  private void updateMonsters(Duration elapsedTime) {
    model.getMonsters().forEach(agent -> agentController.update(agent, elapsedTime));
  }
}
