package com.wensby.terminablo.scene.mainmenu;

import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.scene.Scene;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalLayer;
import com.wensby.userinterface.UserInput;
import java.time.Duration;

public class MainMenuScene implements Scene {

  private final MainMenuController controller;
  private final MainMenuView view;

  public MainMenuScene(
      MainMenuController controller,
      MainMenuView view
  ) {
    this.controller = requireNonNull(controller);
    this.view = requireNonNull(view);
  }

  @Override
  public void update(Duration elapsedTime, UserInput input) {
    controller.update(elapsedTime, input);
  }

  @Override
  public TerminalLayer render(InterfaceSize size) {
    return view.render(size);
  }
}
