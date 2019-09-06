package com.wensby.terminablo.scene.playscene;

import static java.math.BigDecimal.ONE;
import static java.util.Objects.requireNonNull;

import com.wensby.application.userinterface.*;
import com.wensby.application.userinterface.InterfaceLocation;
import com.wensby.terminablo.util.PainterUtils;
import com.wensby.terminablo.util.UnitInterval;

import java.math.BigDecimal;
import org.apache.log4j.Logger;

public class OrbContentTerminalRendererImpl implements OrbContentTerminalRenderer {

  private static final Logger LOGGER = Logger.getLogger(OrbContentTerminalRendererImpl.class);

  private final PartialBlockCharacterFactory partialBlockFactory;
  private final TerminalCharacterFactory characterFactory;

  public OrbContentTerminalRendererImpl(
      PartialBlockCharacterFactory partialBlockFactory,
      TerminalCharacterFactory characterFactory
  ) {
    this.partialBlockFactory = requireNonNull(partialBlockFactory);
    this.characterFactory = requireNonNull(characterFactory);
  }

  @Override
  public void render(Orb orb, TerminalLayer layer) {
    LOGGER.debug("Render orb content: " + orb + ", " + layer.getSize());
    var color = orb.getColor();
    var rows = layer.getSize().getHeight();
    var percent = orb.getValues().toUnitInterval();
    var rowsPercent = BigDecimal.valueOf(rows).multiply(percent);
    var fullRows = rowsPercent.intValue();
    LOGGER.debug("Rendered full rows: " + fullRows);

    if (fullRows > 0) {
      var location = InterfaceLocation.at(0, layer.getSize().getHeight() - fullRows);
      var size = InterfaceSize.of(layer.getSize().getWidth(), fullRows);
      var fullContentSubsectionPainter = layer.getSubsection(location, size);
      var painterUtils = new PainterUtils();
      var character = characterFactory.createCharacter(' ', new CharacterDecoration(color, null, false));
      painterUtils.cover(fullContentSubsectionPainter, character);
    }

    if (fullRows != layer.getSize().getHeight()) {
      var surfaceRowPercent = rowsPercent.divideAndRemainder(ONE)[1].floatValue();
      var character = partialBlockFactory.getPartialBlockCharacter(UnitInterval.of(surfaceRowPercent));
      var surfaceRow = layer.getSize().getHeight() - fullRows - 1;
      var surfaceCharacter = characterFactory.createCharacter(character, new CharacterDecoration(null, color, false));
      for (int x = 0; x < layer.getSize().getWidth(); x++) {
        var position = InterfaceLocation.at(x, surfaceRow);
        layer.put(surfaceCharacter, position);
      }
    }
  }



}
