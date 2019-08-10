package com.wensby.terminablo.userinterface.terminal;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalCharacter;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayer;

public class CharacterDifferenceFactory {

  private TerminalCharacterFactory characterFactory;

  public CharacterDifferenceFactory(final TerminalCharacterFactory characterFactory) {
    this.characterFactory = characterFactory;
  }

  public TerminalCharacter[][] getDifferingCharacters(final TerminalLayer a, final TerminalLayer b) {
    if (a == null) {
      return b.getCharacters();
    }
    else {
      var size = b.getSize();
      var height = size.getHeight();
      var width = size.getWidth();
      var characters = new TerminalCharacter[height][width];
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          characters[y][x] = getDifferingCharacter(a, b, x, y);
        }
      }
      return characters;
    }
  }

  private TerminalCharacter getDifferingCharacter(TerminalLayer a, TerminalLayer b, int x, int y) {
    InterfaceLocation location = InterfaceLocation.of(x, y);
    var prev = containsLocation(a, location) ? a.getCharacter(location) : null;
    var next = containsLocation(b, location) ? b.getCharacter(location) : null;
    if (prev == null) {
      return next;
    }
    else {
      if (next == null) {
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
