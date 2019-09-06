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
  public void render(Orb orb, TerminalLayerPainter painter) {
    LOGGER.debug("Render orb content: " + orb + ", " + painter.getAvailableSize());
    var color = orb.getColor();
    var rows = painter.getAvailableSize().getHeight();
    var percent = orb.getValues().toUnitInterval();
    var rowsPercent = BigDecimal.valueOf(rows).multiply(percent);
    var fullRows = rowsPercent.intValue();
    LOGGER.debug("Rendered full rows: " + fullRows);

    if (fullRows > 0) {
      var location = InterfaceLocation.at(0, painter.getAvailableSize().getHeight() - fullRows);
      var size = InterfaceSize.of(painter.getAvailableSize().getWidth(), fullRows);
      var fullContentSubsectionPainter = painter.createSubsectionPainter(location, size);
      var painterUtils = new PainterUtils();
      var character = characterFactory.createCharacter(' ', new CharacterDecoration(color, null, false));
      painterUtils.cover(fullContentSubsectionPainter, character);
    }

    if (fullRows != painter.getAvailableSize().getHeight()) {
      var surfaceRowPercent = rowsPercent.divideAndRemainder(ONE)[1].floatValue();
      var character = partialBlockFactory.getPartialBlockCharacter(UnitInterval.of(surfaceRowPercent));
      var surfaceRow = painter.getAvailableSize().getHeight() - fullRows - 1;
      var surfaceCharacter = characterFactory.createCharacter(character, new CharacterDecoration(null, color, false));
      for (int x = 0; x < painter.getAvailableSize().getWidth(); x++) {
        var position = InterfaceLocation.at(x, surfaceRow);
        painter.put(surfaceCharacter, position);
      }
    }
  }



}
