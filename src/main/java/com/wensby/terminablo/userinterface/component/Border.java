package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.InterfaceLocation;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayerPainter;

public class Border implements InterfaceComponent {

  private final TerminalCharacterFactory characterFactory;
  private final InterfaceComponent child;

  public Border(TerminalCharacterFactory characterFactory, InterfaceComponent child) {
    this.characterFactory = characterFactory;
    this.child = child;
  }

  @Override
  public void render(TerminalLayerPainter painter) {
    painter.put(characterFactory.createCharacter('▒'), InterfaceLocation.atOrigin());
    painter.put(characterFactory.createCharacter('▒'), InterfaceLocation.at(0, painter.getAvailableSize().getHeight()-1));
    painter.put(characterFactory.createCharacter('▒'), InterfaceLocation.at(painter.getAvailableSize().getWidth()-1, 0));
    painter.put(characterFactory.createCharacter('▒'), InterfaceLocation.at(painter.getAvailableSize().getWidth()-1, painter.getAvailableSize().getHeight()-1));
    for (int x = 1; x < painter.getAvailableSize().getWidth() - 1; x++) {
      painter.put(characterFactory.createCharacter('─'), InterfaceLocation.at(x, 0));
      painter.put(characterFactory.createCharacter('─'), InterfaceLocation.at(x, painter.getAvailableSize().getHeight()-1));
    }
    for (int y = 1; y < painter.getAvailableSize().getHeight() - 1; y++) {
      painter.put(characterFactory.createCharacter('║'), InterfaceLocation.at(0, y));
      painter.put(characterFactory.createCharacter('║'), InterfaceLocation.at(painter.getAvailableSize().getWidth()-1, y));
    }
    child.render(painter);
  }
}
