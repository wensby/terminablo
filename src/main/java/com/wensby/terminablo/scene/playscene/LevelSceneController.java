package com.wensby.terminablo.scene.playscene;

import static com.wensby.terminablo.userinterface.Key.ESCAPE;

import com.wensby.terminablo.scene.SceneStack;
import com.wensby.userinterface.UserInput;
import java.math.BigDecimal;
import java.time.Duration;

public class LevelSceneController implements Controller {

  private final SceneStack sceneStack;
  private final AgentController agentController;
  private final LevelSceneModel model;
  private final PlayerCombatController playerCombatController;
  private final PlayerMovementController playerMovementController;

  public LevelSceneController(SceneStack sceneStack,
      AgentController agentController, LevelSceneModel model,
      PlayerMovementController playerMovementController,
      PlayerCombatController playerCombatController) {
    this.sceneStack = sceneStack;
    this.agentController = agentController;
    this.playerMovementController = playerMovementController;
    this.model = model;
    this.playerCombatController = playerCombatController;
  }

  @Override
  public void update(Duration elapsedTime, UserInput input) {
    BigDecimal plus = model.getHero().getStats().getLife().add(BigDecimal.TEN);
    model.getHero().getStats().setLife(plus);
    if (input.getKeyStrokes().contains(ESCAPE)) {
      sceneStack.pop();
    }
    updateMonsters(elapsedTime);
    playerCombatController.update(elapsedTime, input);
    playerMovementController.update(elapsedTime, input);
  }

  private void updateMonsters(Duration elapsedTime) {
    model.getMonsters().forEach(agent -> agentController.update(agent, elapsedTime));
  }
}
