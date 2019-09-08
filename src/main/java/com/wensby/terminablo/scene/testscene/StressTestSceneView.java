package com.wensby.terminablo.scene.testscene;

import com.wensby.application.userinterface.CharacterDecoration;
import com.wensby.application.userinterface.TerminalCharacter;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayer;
import com.wensby.terminablo.scene.View;
import com.wensby.terminablo.util.PainterUtils;

import java.awt.*;
import java.util.Random;


public class StressTestSceneView implements View {

  private final TerminalCharacterFactory characterFactory;

  public StressTestSceneView(TerminalCharacterFactory characterFactory) {
    this.characterFactory = characterFactory;
  }

  @Override
  public void render(TerminalLayer layer) {
    new PainterUtils().cover(layer, this::getRandomTerminalCharacter);
  }

  private TerminalCharacter getRandomTerminalCharacter() {
    var random = new Random();
    var character = (char) (random.nextInt(25) + 65);
    var backgroundColor = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    var foregroundColor = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    var decoration = new CharacterDecoration(backgroundColor, foregroundColor, random.nextBoolean());
    return characterFactory.createCharacter(character, decoration);
  }
}
