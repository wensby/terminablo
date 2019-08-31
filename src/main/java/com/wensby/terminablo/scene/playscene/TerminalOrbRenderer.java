package com.wensby.terminablo.scene.playscene;

import static java.util.Objects.requireNonNull;

import com.wensby.application.userinterface.*;
import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.terminablo.userinterface.smartstring.NumberedString;
import org.apache.log4j.Logger;

public class TerminalOrbRenderer {

  private static final Logger LOGGER = Logger.getLogger(TerminalOrbRenderer.class);
  private final TerminalCharacterFactory characterFactory;
  private final OrbContentTerminalRenderer orbContentTerminalRenderer;

  public TerminalOrbRenderer(
      TerminalCharacterFactory characterFactory,
      OrbContentTerminalRenderer orbContentTerminalRenderer
  ) {
    this.characterFactory = requireNonNull(characterFactory);
    this.orbContentTerminalRenderer = requireNonNull(orbContentTerminalRenderer);
  }

  public void render(Orb orb, TerminalLayerPainter painter) {
    LOGGER.debug("Create Orb Representation: " + orb + ", " + painter);
    if (isBordered(painter.getAvailableSize())) {
      createFullSizeRepresentation(orb, painter);
    } else {
      createScaledDownRepresentation(orb, painter);
    }
  }

  private void createScaledDownRepresentation(Orb orb, TerminalLayerPainter painter) {
    orbContentTerminalRenderer.render(orb, painter);
  }

  private void createFullSizeRepresentation(Orb orb, TerminalLayerPainter painter) {
    fillInBorder(painter);
    var orbContentSize = painter.getAvailableSize().minus(InterfaceSize.of(2, 2));
    var contentPainter = painter.createSubsectionPainter(InterfaceLocation.at(1, 1), orbContentSize);
    orbContentTerminalRenderer.render(orb, contentPainter);
    fillInValue(painter, orb);
  }

  private void fillInBorder(TerminalLayerPainter painter) {
    if (isBordered(painter.getAvailableSize())) {
      var height = painter.getAvailableSize().getHeight();
      var width = painter.getAvailableSize().getWidth();
      for (int i = 1; i < width - 1; i++) {
        painter.put(characterFactory.createCharacter('─'), InterfaceLocation.at(i, 0));
      }
      painter.put(characterFactory.createCharacter('╭'), InterfaceLocation.at(0, 0));
      painter.put(characterFactory.createCharacter('╮'), InterfaceLocation.at(width - 1, 0));
      for (int i = 1; i < height - 1; i++) {
        painter.put(characterFactory.createCharacter('│'), InterfaceLocation.at(0, i));
        painter.put(characterFactory.createCharacter('│'), InterfaceLocation.at(width - 1, i));
      }
      painter.put(characterFactory.createCharacter('╰'), InterfaceLocation.at(0, height - 1));
      painter.put(characterFactory.createCharacter('╯'), InterfaceLocation.at(width - 1, height - 1));
    }
  }

  private boolean isBordered(InterfaceSize size) {
    return size.getWidth() > 2 && size.getHeight() > 2;
  }

  private void fillInValue(TerminalLayerPainter painter, Orb orb) {
    var height = painter.getAvailableSize().getHeight();
    var width = painter.getAvailableSize().getWidth();
    if (painter.getAvailableSize().getWidth() >= 6 && painter.getAvailableSize().getHeight() > 1) {
      var s = NumberedString.format("%s/%s", orb.getValues().getNumerator(), orb.getValues().getDenominator());
      s = s.length() > 6 ? s.substring(0, 6) : s;
      var start = (width - s.length()) / 2;
      for (int i = 0; i < s.length(); i++) {
        painter.put(characterFactory.createCharacter(s.charAt(i)), InterfaceLocation.at(i + start, height - 1));
      }
    }
  }
}
