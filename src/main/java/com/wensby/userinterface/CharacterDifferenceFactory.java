package com.wensby.userinterface;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.userinterface.TerminalCharacter;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CharacterDifferenceFactory {

  private final TerminalCharacterFactory characterFactory;

  public CharacterDifferenceFactory(TerminalCharacterFactory characterFactory) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
  }

  public Map<InterfaceLocation, TerminalCharacter> getDifference(TerminalLayer a, TerminalLayer b) {
    var difference = new HashMap<InterfaceLocation, TerminalCharacter>();

    var size = b.getSize();
    var height = size.getHeight();
    var width = size.getWidth();
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        var location = InterfaceLocation.of(column, row);
        var differingCharacter = getDifferingCharacter(a, b, location);
        if (differingCharacter != null) {
          difference.put(location, differingCharacter);
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
