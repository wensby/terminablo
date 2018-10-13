package com.wensby.userinterface;

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
    return new InterfaceSize(characters[0].length, characters.length);
  }

  @Override
  public void put(TerminalLayer layer, InterfacePosition target) {
    var size = layer.getSize();
    for (int y = 0; y < size.getHeight(); y++) {
      for (int x = 0; x < size.getWidth(); x++) {
        var originPosition = InterfacePosition.of(x, y);
        var character = layer.getCharacter(originPosition);
        if (character != null) {
          var destinationPosition = originPosition.plus(target);
          put(destinationPosition, character);
        }
      }
    }
  }

  @Override
  public void put(InterfacePosition position, TerminalCharacter character) {
    characters[position.getRow()][position.getColumn()] = character;
  }

  @Override
  public TerminalCharacter getCharacter(InterfacePosition interfacePosition) {
    return characters[interfacePosition.getRow()][interfacePosition.getColumn()];
  }
}
