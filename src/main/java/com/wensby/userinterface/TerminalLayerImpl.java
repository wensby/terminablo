package com.wensby.userinterface;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;

public class TerminalLayerImpl implements TerminalLayer {

  private final TerminalCharacter[][] characters;

  public TerminalLayerImpl(TerminalCharacter[][] characters) {
    this.characters = characters.clone();
  }

  @Override
  public TerminalCharacter[][] getCharacters() {
    return characters.clone();
  }

  @Override
  public InterfaceSize getSize() {
    return InterfaceSize.of(characters[0].length, characters.length);
  }

  @Override
  public void put(TerminalLayer layer, InterfaceLocation target) {
    var size = layer.getSize();
    for (int row = 0; row < size.getHeight(); row++) {
      for (int column = 0; column < size.getWidth(); column++) {
        var originLocation = InterfaceLocation.of(column, row);
        var character = layer.getCharacter(originLocation);
        if (character != null) {
          var destinationPosition = originLocation.plus(target);
          put(destinationPosition, character);
        }
      }
    }
  }

  @Override
  public boolean put(InterfaceLocation position, TerminalCharacter character) {
    var row = position.getRow();
    if (row <= characters.length) {
      var column = position.getColumn();
      if (column <= characters[row].length) {
        characters[row][column] = character;
        return true;
      }
    }
    return false;
  }

  @Override
  public TerminalCharacter getCharacter(InterfaceLocation interfacePosition) {
    return characters[interfacePosition.getRow()][interfacePosition.getColumn()];
  }
}
