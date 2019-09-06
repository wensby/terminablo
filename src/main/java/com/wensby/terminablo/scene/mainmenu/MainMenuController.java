package com.wensby.terminablo.scene.mainmenu;

import static com.wensby.application.userinterface.Key.*;
import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.scene.SceneStack;
import com.wensby.terminablo.scene.playscene.Controller;
import com.wensby.terminablo.scene.playscene.PlaySceneFactory;
import com.wensby.application.userinterface.UserInput;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class MainMenuController implements Controller {

  private final SceneStack sceneStack;
  private final MainMenuModel model;
  private final PlaySceneFactory playSceneFactory;

  public MainMenuController(SceneStack sceneStack, MainMenuModel model, PlaySceneFactory playSceneFactory) {
    this.sceneStack = Objects.requireNonNull(sceneStack);
    this.model = requireNonNull(model);
    this.playSceneFactory = requireNonNull(playSceneFactory);
  }

  @Override
  public void update(Duration elapsedTime, UserInput input) {
    final int i = model.getMenuItems().size();
    if (input.getKeyStrokes().contains(ARROW_DOWN)) {
      if (model.getSelectedMenu().equalsIgnoreCase("CREDITS")) {
        model.selectNextItem();
      }
      model.selectNextItem();
    }
    if (input.getKeyStrokes().contains(ARROW_UP)) {
      if (List.of("CINEMATICS", "EXIT TERMINABLO").contains(model.getSelectedMenu())) {
        model.selectPreviousItem();
      }
      model.selectPreviousItem();
    }
    if (input.getKeyStrokes().contains(ARROW_RIGHT) && model.getSelectedMenu().equalsIgnoreCase("CREDITS")) {
      model.selectNextItem();
    }
    if (input.getKeyStrokes().contains(ARROW_LEFT) && model.getSelectedMenu().equalsIgnoreCase("CINEMATICS")) {
      model.selectPreviousItem();
    }
    if (input.getKeyStrokes().contains(CARRIAGE_RETURN)) {
      if (model.getSelectedMenu().equalsIgnoreCase("Single Player")) {
        var levelScene = playSceneFactory.createPlayScene();
        sceneStack.push(levelScene);
      }
      else if (model.getSelectedMenu().equalsIgnoreCase("exit terminablo")) {
        sceneStack.pop();
      }
    }
  }
}
