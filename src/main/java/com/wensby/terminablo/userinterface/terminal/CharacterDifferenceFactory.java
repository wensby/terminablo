package com.wensby.terminablo.userinterface.terminal;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalCharacter;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayer;

import java.util.HashMap;
import java.util.Map;

public class CharacterDifferenceFactory {

  private TerminalCharacterFactory characterFactory;

  public CharacterDifferenceFactory(final TerminalCharacterFactory characterFactory) {
    this.characterFactory = characterFactory;
  }

  public Map<InterfaceLocation, TerminalCharacter> getDifferingCharacters(TerminalLayer a, TerminalLayer b) {
    var size = b.getSize();
    var height = size.getHeight();
    var width = size.getWidth();
    var difference = new HashMap<InterfaceLocation, TerminalCharacter>();
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        var location = InterfaceLocation.of(x, y);
        var differingCharacter = getDifferingCharacter(a, b, location);
        if (differingCharacter != null) {
          difference.put(location, differingCharacter);
        }
      }
    }
    return difference;
  }

  private TerminalCharacter getDifferingCharacter(TerminalLayer a, TerminalLayer b, InterfaceLocation location) {
    var prev = a == null ? null : (containsLocation(a, location) ? a.getCharacter(location) : null);
    var next = containsLocation(b, location) ? b.getCharacter(location) : null;
    if (prev == null) {
      return next;
    }
    else {
      if (next == null) {
        if (prev.isLong()) {
          return characterFactory.createCharacter("  ");
        }
        return characterFactory.createCharacter(' ');
      }
      else {
        return next;
      }
    }
  }

  private boolean containsLocation(TerminalLayer layer, InterfaceLocation location) {
    InterfaceSize size = layer.getSize();
    return location.getRow() < size.getHeight() && location.getColumn() < size.getWidth();
  }
}
