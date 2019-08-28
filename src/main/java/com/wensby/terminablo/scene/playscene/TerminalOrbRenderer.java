package com.wensby.terminablo.scene.playscene;

import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayer;
import com.wensby.application.userinterface.TerminalLayerFactory;
import com.wensby.userinterface.smartstring.NumberedString;
import org.apache.log4j.Logger;

public class TerminalOrbRenderer {

  private static final Logger LOGGER = Logger.getLogger(TerminalOrbRenderer.class);
  private final TerminalLayerFactory layerFactory;
  private final TerminalCharacterFactory characterFactory;
  private final OrbContentTerminalRenderer orbContentTerminalRenderer;

  public TerminalOrbRenderer(
      TerminalLayerFactory layerFactory,
      TerminalCharacterFactory characterFactory,
      OrbContentTerminalRenderer orbContentTerminalRenderer
  ) {
    this.layerFactory = requireNonNull(layerFactory);
    this.characterFactory = requireNonNull(characterFactory);
    this.orbContentTerminalRenderer = requireNonNull(orbContentTerminalRenderer);
  }

  public TerminalLayer render(Orb orb, InterfaceSize size) {
    LOGGER.debug("Create Orb Representation: " + orb + ", " + size);
    if (isBordered(size)) {
      return createFullSizeRepresentation(orb, size);
    } else {
      return createScaledDownRepresentation(orb, size);
    }
  }

  private TerminalLayer createScaledDownRepresentation(Orb orb, InterfaceSize size) {
    var layer = layerFactory.createBlankLayer(size);
    var orbContent = orbContentTerminalRenderer.render(orb, size);
    layer.put(orbContent, InterfaceLocation.atOrigin());
    return layer;
  }

  private TerminalLayer createFullSizeRepresentation(Orb orb, InterfaceSize size) {
    var layer = layerFactory.createBlankLayer(size);
    fillInBorder(layer);
    var orbContentSize = size.minus(InterfaceSize.of(2, 2));
    var orbContent = orbContentTerminalRenderer.render(orb, orbContentSize);
    layer.put(orbContent, InterfaceLocation.at(1, 1));
    fillInValue(layer, orb);
    return layer;
  }

  private void fillInBorder(TerminalLayer layer) {
    if (isBordered(layer.getSize())) {
      var height = layer.getSize().getHeight();
      var width = layer.getSize().getWidth();
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
    if (layer.getSize().getWidth() >= 6 && layer.getSize().getHeight() > 1) {
      var height = layer.getSize().getHeight();
      var width = layer.getSize().getWidth();
      var s = NumberedString.format("%s/%s", orb.getValues().getNumerator(), orb.getValues().getDenominator());
      s = s.length() > 6 ? s.substring(0, 6) : s;
      var start = (width - s.length()) / 2;
      for (int i = 0; i < s.length(); i++) {
        layer.put(characterFactory.createCharacter(s.charAt(i)), InterfaceLocation.at(i + start, height - 1));
      }
    }
  }
}
