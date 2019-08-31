package com.wensby.terminablo.scene;

import com.wensby.application.userinterface.TerminalLayerPainter;
import com.wensby.terminablo.scene.playscene.Controller;
import com.wensby.application.userinterface.UserInput;
import java.time.Duration;

public class Scene {

  private final Controller controller;
  private final View view;

  public Scene(Controller controller, View view) {
    this.controller = controller;
    this.view = view;
  }

  public void update(Duration elapsedTime, UserInput input) {
    controller.update(elapsedTime, input);
  }

  public void render(TerminalLayerPainter painter) {
    view.render(painter);
  }
}
