package com.wensby.terminablo.scene.playscene;

import static java.util.Objects.requireNonNull;

import com.wensby.application.userinterface.*;
import com.wensby.application.userinterface.InterfaceLocation;
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

  public void render(Orb orb, TerminalLayer layer) {
    LOGGER.debug("Create Orb Representation: " + orb + ", " + layer);
    if (isBordered(layer.size())) {
      createFullSizeRepresentation(orb, layer);
    } else {
      createScaledDownRepresentation(orb, layer);
    }
  }

  private void createScaledDownRepresentation(Orb orb, TerminalLayer layer) {
    orbContentTerminalRenderer.render(orb, layer);
  }

  private void createFullSizeRepresentation(Orb orb, TerminalLayer layer) {
    fillInBorder(layer);
    var orbContentSize = layer.size().minus(InterfaceSize.of(2, 2));
    var contentPainter = layer.getSubsection(InterfaceLocation.at(1, 1), orbContentSize);
    orbContentTerminalRenderer.render(orb, contentPainter);
    fillInValue(layer, orb);
  }

  private void fillInBorder(TerminalLayer layer) {
    if (isBordered(layer.size())) {
      var height = layer.size().getHeight();
      var width = layer.size().getWidth();
      for (int i = 1; i < width - 1; i++) {
        layer.put(characterFactory.createCharacter('─'), InterfaceLocation.at(i, 0));
      }
      layer.put(characterFactory.createCharacter('╭'), InterfaceLocation.at(0, 0));
      layer.put(characterFactory.createCharacter('╮'), InterfaceLocation.at(width - 1, 0));
      for (int i = 1; i < height - 1; i++) {
        layer.put(characterFactory.createCharacter('│'), InterfaceLocation.at(0, i));
        layer.put(characterFactory.createCharacter('│'), InterfaceLocation.at(width - 1, i));
      }
      layer.put(characterFactory.createCharacter('╰'), InterfaceLocation.at(0, height - 1));
      layer.put(characterFactory.createCharacter('╯'), InterfaceLocation.at(width - 1, height - 1));
    }
  }

  private boolean isBordered(InterfaceSize size) {
    return size.getWidth() > 2 && size.getHeight() > 2;
  }

  private void fillInValue(TerminalLayer layer, Orb orb) {
    var height = layer.size().getHeight();
    var width = layer.size().getWidth();
    if (layer.size().getWidth() >= 6 && layer.size().getHeight() > 1) {
      var s = NumberedString.format("%s/%s", orb.getValues().getNumerator(), orb.getValues().getDenominator());
      s = s.length() > 6 ? s.substring(0, 6) : s;
      var start = (width - s.length()) / 2;
      for (int i = 0; i < s.length(); i++) {
        layer.put(characterFactory.createCharacter(s.charAt(i)), InterfaceLocation.at(i + start, height - 1));
      }
    }
  }
}
