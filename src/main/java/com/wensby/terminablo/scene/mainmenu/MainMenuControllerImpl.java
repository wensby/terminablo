package com.wensby.terminablo.scene.mainmenu;

import static com.wensby.application.userinterface.Key.*;
import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.scene.SceneStack;
import com.wensby.terminablo.scene.playscene.PlaySceneFactory;
import com.wensby.application.userinterface.UserInput;
import java.time.Duration;

public class MainMenuControllerImpl implements MainMenuController {

  private final MainMenuModel model;
  private final SceneStack sceneStack;
  private final PlaySceneFactory playSceneFactory;

  public MainMenuControllerImpl(
      MainMenuModel model,
      SceneStack sceneStack,
      PlaySceneFactory playSceneFactory
  ) {
    this.model = requireNonNull(model);
    this.sceneStack = sceneStack;
    this.playSceneFactory = requireNonNull(playSceneFactory);
  }

  @Override
  public void update(Duration elapsedTime, UserInput input) {
    final int i = model.getMenuItems().size();
    if (input.getKeyStrokes().contains(ARROW_DOWN)) {
      model.setSelectedMenuItemIndex((i + model.getSelectedMenuItemIndex() + 1) % i);
    }
    if (input.getKeyStrokes().contains(ARROW_UP)) {
      model.setSelectedMenuItemIndex((i + model.getSelectedMenuItemIndex() - 1) % i);
    }
    if (input.getKeyStrokes().contains(CARRIAGE_RETURN)) {
      if (model.getSelectedMenuItemIndex() == 1) {
        var levelScene = playSceneFactory.createPlayScene();
        sceneStack.push(levelScene);
      }
      else if (model.getSelectedMenuItemIndex() == i-1) {
        sceneStack.pop();
      }
    }
  }
}
