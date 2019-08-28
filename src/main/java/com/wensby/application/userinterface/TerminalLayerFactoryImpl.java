package com.wensby.application.userinterface;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;

import java.awt.*;

import static java.util.Objects.requireNonNull;

public class TerminalLayerFactoryImpl implements TerminalLayerFactory {

  private final TerminalCharacterFactory characterFactory;

  public TerminalLayerFactoryImpl(TerminalCharacterFactory characterFactory) {
    this.characterFactory = requireNonNull(characterFactory);
  }

  @Override
  public TerminalLayer createBlankLayer(InterfaceSize size) {
    return new SparseLayer(size);
  }

  @Override
  public TerminalLayer createColoredLayer(InterfaceSize size, Color color) {
    var layer = createBlankLayer(size);
    var character = characterFactory.createCharacter(' ', new CharacterDecoration(color, null));
    for (int row = 0; row < size.getHeight(); row++) {
      paintLayerRow(layer, row, character);
    }
    return layer;
  }

  private void paintLayerRow(TerminalLayer layer, int row, TerminalCharacter character) {
    for (int column = 0; column < layer.getSize().getWidth(); column++) {
      var position = InterfaceLocation.at(column, row);
      layer.put(character, position);
    }
  }
}
