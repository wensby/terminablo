package com.wensby.terminablo.scene.playscene;

import static java.math.BigDecimal.ONE;
import static java.util.Objects.requireNonNull;

import com.wensby.application.userinterface.*;
import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.terminablo.util.UnitInterval;
import java.math.BigDecimal;
import org.apache.log4j.Logger;

public class OrbContentTerminalRendererImpl implements OrbContentTerminalRenderer {

  private static final Logger LOGGER = Logger.getLogger(OrbContentTerminalRendererImpl.class);

  private final PartialBlockCharacterFactory partialBlockFactory;
  private final TerminalLayerFactory layerFactory;
  private final TerminalCharacterFactory characterFactory;

  public OrbContentTerminalRendererImpl(
      PartialBlockCharacterFactory partialBlockFactory,
      TerminalLayerFactory layerFactory,
      TerminalCharacterFactory characterFactory
  ) {
    this.partialBlockFactory = requireNonNull(partialBlockFactory);
    this.layerFactory = requireNonNull(layerFactory);
    this.characterFactory = requireNonNull(characterFactory);
  }

  @Override
  public TerminalLayer render(Orb orb, InterfaceSize size) {
    LOGGER.debug("Render orb content: " + orb + ", " + size);
    var layer = layerFactory.createBlankLayer(size);
    var color = orb.getColor();
    var rows = size.getHeight();
    var percent = orb.getValues().toUnitInterval();
    var rowsPercent = BigDecimal.valueOf(rows).multiply(percent);
    var fullRows = rowsPercent.intValue();
    LOGGER.debug("Rendered full rows: " + fullRows);

    if (fullRows > 0) {
      var fullColorLayerSize = InterfaceSize.of(size.getWidth(), fullRows);
      var fullColorLayer = layerFactory.createColoredLayer(fullColorLayerSize, color);
      var topOffset = InterfaceLocation.at(0, size.getHeight() - fullRows);
      layer.put(fullColorLayer, topOffset);
    }

    if (fullRows != size.getHeight()) {
      var surfaceRowPercent = rowsPercent.divideAndRemainder(ONE)[1].floatValue();
      var character = partialBlockFactory.getPartialBlockCharacter(UnitInterval.of(surfaceRowPercent));
      var surfaceRow = size.getHeight() - fullRows - 1;
      var surfaceCharacter = characterFactory.createCharacter(character, new CharacterDecoration(null, color));
      for (int x = 0; x < size.getWidth(); x++) {
        var position = InterfaceLocation.at(x, surfaceRow);
        layer.put(surfaceCharacter, position);
      }
    }

    return layer;
  }
}
