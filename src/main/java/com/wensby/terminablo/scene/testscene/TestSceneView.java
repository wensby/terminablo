package com.wensby.terminablo.scene.testscene;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayerPainter;
import com.wensby.terminablo.scene.View;
import com.wensby.terminablo.scene.mainmenu.MainMenu;
import com.wensby.terminablo.userinterface.UserInterface;



public class TestSceneView implements View {

  private final UserInterface userInterface;

  public TestSceneView(TerminalCharacterFactory characterFactory) {
    this.userInterface = new UserInterface(new MainMenu(characterFactory));
  }

  @Override
  public void render(TerminalLayerPainter painter) {
    userInterface.getTopComponent().render(painter);
  }
}
