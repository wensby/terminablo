package com.wensby.terminablo.scene.levelscene;

import static java.math.BigDecimal.ONE;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Objects.requireNonNull;

import com.wensby.userinterface.InterfacePosition;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayer;
import com.wensby.userinterface.TerminalLayerFactory;
import com.wensby.util.UnitInterval;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    var percent = orb.getValue().divide(orb.getCapacity(), HALF_UP);
    var rowsPercent = BigDecimal.valueOf(rows).multiply(percent);
    var fullRows = rowsPercent.intValue();

    if (fullRows > 0) {
      var fullColorLayerSize = InterfaceSize.of(size.getWidth(), fullRows);
      var fullColorLayer = layerFactory.createColoredLayer(fullColorLayerSize, color);
      var topOffset = InterfacePosition.of(0, size.getHeight() - fullRows);
      layer.put(topOffset, fullColorLayer);
    }

    var surfaceRowPercent = rowsPercent.divideAndRemainder(ONE)[1].floatValue();
    if (fullRows != size.getHeight()) {
      var character = partialBlockFactory.getPartialBlockCharacter(UnitInterval.of(surfaceRowPercent));
      var surfaceRow = size.getHeight() - fullRows - 1;
      var surfaceCharacter = characterFactory.createCharacter(character, color, null);
      for (int x = 0; x < size.getWidth(); x++) {
        var position = InterfacePosition.of(x, surfaceRow);
        layer.put(position, surfaceCharacter);
      }
    }

    return layer;
  }
}
