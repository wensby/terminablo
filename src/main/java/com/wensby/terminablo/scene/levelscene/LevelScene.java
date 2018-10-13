package com.wensby.terminablo.scene.levelscene;

import com.wensby.terminablo.Scene;
import com.wensby.userinterface.UserInput;
import java.time.Duration;

public class LevelScene implements Scene {

  private final LevelSceneController controller;
  private final LevelSceneView view;

  public LevelScene(LevelSceneController controller, LevelSceneView view) {
    this.controller = controller;
    this.view = view;
  }

  @Override
  public Scene update(Duration elapsedTime, UserInput input) {
    controller.update(elapsedTime, input);
    view.render();
    return this;
  }
}
