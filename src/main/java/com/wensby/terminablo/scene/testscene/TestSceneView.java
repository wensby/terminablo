package com.wensby.terminablo.scene.testscene;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayerPainter;
import com.wensby.terminablo.scene.View;
import com.wensby.terminablo.scene.mainmenu.MainMenuButton;
import com.wensby.terminablo.userinterface.UserInterface;


import static java.awt.Color.*;

public class TestSceneView implements View {

  private final UserInterface userInterface;

  public TestSceneView(TerminalCharacterFactory characterFactory) {
    this.userInterface = new UserInterface(new MainMenuButton(characterFactory, "myLabel"));
  }

  @Override
  public void render(TerminalLayerPainter painter) {
    userInterface.getTopComponent().render(painter);
  }
}
