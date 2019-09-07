package com.wensby.terminablo.scene.testscene;

import com.wensby.application.userinterface.TerminalLayer;
import com.wensby.terminablo.scene.View;
import com.wensby.terminablo.userinterface.UserInterface;



public class TestSceneView implements View {

  private final UserInterface userInterface;

  public TestSceneView(UserInterface userInterface) {
    this.userInterface = userInterface;
  }

  @Override
  public void render(TerminalLayer layer) {
    userInterface.render(layer);
  }
}
