package com.wensby.application.userinterface;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LayerDifferenceCalculator {

  private final TerminalCharacterFactory characterFactory;

  public LayerDifferenceCalculator(TerminalCharacterFactory characterFactory) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
  }

  /**
   * @return a list at positioned characters, sorted by column and row.
   */
  public List<PositionedTerminalCharacter> getDifference(TerminalLayer a, TerminalLayer b) {
    var difference = new ArrayList<PositionedTerminalCharacter>();

    for (int row = 0; row < b.getSize().getHeight(); row++) {
      for (int column = 0; column < b.getSize().getWidth(); column++) {
        var location = InterfaceLocation.at(column, row);
        var differingCharacter = getDifferingCharacter(a, b, location);
        if (differingCharacter != null) {
          difference.add(new PositionedTerminalCharacter(location, differingCharacter));
        }
      }
    }

    return difference;
  }

  private TerminalCharacter getDifferingCharacter(TerminalLayer a, TerminalLayer b, InterfaceLocation location) {
    var prev = getCharacter(a, location);
    var next = getCharacter(b, location);
    if (prev == null) {
      return next;
    }
    else {
      if (next == null) {
        if (prev instanceof ComplexTerminalCharacter && ((ComplexTerminalCharacter) prev).getCharSequence().length() > 1) {
          return characterFactory.createCharacter("  ");
        }
        return characterFactory.createCharacter(' ');
      }
      else {
        return next;
      }
    }
  }

  private TerminalCharacter getCharacter(TerminalLayer layer, InterfaceLocation location) {
    if (layer != null && containsLocation(layer, location)) {
      return layer.getCharacter(location);
    }
    else {
      return null;
    }
  }

  private boolean containsLocation(TerminalLayer layer, InterfaceLocation location) {
    var size = layer.getSize();
    return location.getRow() < size.getHeight() && location.getColumn() < size.getWidth();
  }
}
