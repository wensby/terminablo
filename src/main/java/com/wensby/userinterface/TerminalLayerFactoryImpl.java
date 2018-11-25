package com.wensby.userinterface;

import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import java.awt.Color;

public class TerminalLayerFactoryImpl implements TerminalLayerFactory {

  private final TerminalCharacterFactory characterFactory;

  public TerminalLayerFactoryImpl(TerminalCharacterFactory characterFactory) {
    this.characterFactory = requireNonNull(characterFactory);
  }

  @Override
  public TerminalLayer createBlankLayer(InterfaceSize size) {
    TerminalCharacter[][] characters = new TerminalCharacter[size.getHeight()][size.getWidth()];
    return new TerminalLayerImpl(characters);
  }

  @Override
  public TerminalLayer createColoredLayer(InterfaceSize size, Color color) {
    var layer = createBlankLayer(size);
    var character = characterFactory.createCharacter(' ', null, color);
    for (int row = 0; row < size.getHeight(); row++) {
      paintLayerRow(layer, row, character);
    }
    return layer;
  }

  private void paintLayerRow(TerminalLayer layer, int row, TerminalCharacter character) {
    for (int column = 0; column < layer.getSize().getWidth(); column++) {
      var position = InterfaceLocation.of(column, row);
      layer.put(character, position);
    }
  }
}
