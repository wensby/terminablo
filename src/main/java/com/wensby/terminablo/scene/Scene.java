package com.wensby.terminablo.scene;

import com.wensby.terminablo.scene.playscene.Controller;
import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayer;
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

  public TerminalLayer render(InterfaceSize size) {
    return view.render(size);
  }
}
