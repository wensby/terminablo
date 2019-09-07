package com.wensby.terminablo.scene.mainmenu;

import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.scene.playscene.Controller;
import com.wensby.application.userinterface.UserInput;
import java.time.Duration;

public class MainMenuController implements Controller {

  private final MainMenuModel model;

  public MainMenuController(MainMenuModel model) {
    this.model = requireNonNull(model);
  }

  @Override
  public void update(Duration elapsedTime, UserInput input) {
    model.getUserInterface().update(elapsedTime, input);
  }
}
