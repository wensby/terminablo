package com.wensby.terminablo.scene.testscene;

import com.wensby.application.userinterface.CharacterDecoration;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayerPainter;
import com.wensby.terminablo.scene.View;
import com.wensby.terminablo.util.PainterUtils;


import static java.awt.Color.*;

public class TestSceneView implements View {

  private final TerminalCharacterFactory characterFactory;

  public TestSceneView(TerminalCharacterFactory characterFactory) {
    this.characterFactory = characterFactory;
  }

  @Override
  public void render(TerminalLayerPainter painter) {
    var character = characterFactory.createCharacter(' ', new CharacterDecoration(RED, YELLOW, false));
    new PainterUtils().cover(painter, character);
  }
}
