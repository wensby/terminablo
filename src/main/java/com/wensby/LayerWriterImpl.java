package com.wensby;

import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.userinterface.InterfaceRegion;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayer;

public class LayerWriterImpl implements LayerWriter {

  private final TerminalCharacterFactory characterFactory;
  private final TerminalLayer layer;

  public LayerWriterImpl(TerminalCharacterFactory characterFactory, TerminalLayer layer) {
    this.characterFactory = requireNonNull(characterFactory);
    this.layer = requireNonNull(layer);
  }

  @Override
  public void write(String text, InterfaceLocation location) {
    requireNonNull(location);
    requireNonNull(text);
    var row = location.getRow();
    var column = location.getColumn();
    boolean put = true;
    for (int i = 0; put && i < text.length(); i++) {
      var characterLocation = InterfaceLocation.of(column, row);
      var character = text.charAt(i);
      if (character != '\n') {
        var terminalCharacter = characterFactory.createCharacter(character);
        put = layer.put(terminalCharacter, characterLocation);
        column = column + 1;
      }
      else {
        row = characterLocation.getRow() + 1;
        column = location.getColumn();
      }
    }
  }

  @Override
  public void write(InterfaceRegion interfaceRegion, String text) {

  }
}
