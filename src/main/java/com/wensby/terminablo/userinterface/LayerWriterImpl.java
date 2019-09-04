package com.wensby.terminablo.userinterface;

import static java.util.Objects.requireNonNull;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayerPainter;
import com.wensby.application.userinterface.InterfaceLocation;

public class LayerWriterImpl implements LayerWriter {

  private final TerminalCharacterFactory characterFactory;
  private final TerminalLayerPainter painter;

  public LayerWriterImpl(TerminalCharacterFactory characterFactory, TerminalLayerPainter painter) {
    this.characterFactory = requireNonNull(characterFactory);
    this.painter = requireNonNull(painter);
  }

  @Override
  public void write(String text, InterfaceLocation location) {
    requireNonNull(location);
    requireNonNull(text);
    var row = location.getRow();
    var column = location.getColumn();
    boolean put = true;
    for (int i = 0; put && i < text.length(); i++) {
      var characterLocation = InterfaceLocation.at(column, row);
      var character = text.charAt(i);
      if (character != '\n') {
        var terminalCharacter = characterFactory.createCharacter(character);
        put = painter.put(terminalCharacter, characterLocation);
        column = column + 1;
      }
      else {
        row = characterLocation.getRow() + 1;
        column = location.getColumn();
      }
    }
  }

}
