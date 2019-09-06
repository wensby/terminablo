package com.wensby.terminablo.userinterface;

import static java.util.Objects.requireNonNull;

import com.wensby.application.userinterface.*;

import java.awt.*;

public class LayerWriterImpl implements LayerWriter {

  private final TerminalCharacterFactory characterFactory;
  private final TerminalLayerPainter painter;
  private final boolean bold;

  public LayerWriterImpl(TerminalCharacterFactory characterFactory, TerminalLayerPainter painter, boolean bold) {
    this.characterFactory = requireNonNull(characterFactory);
    this.painter = requireNonNull(painter);
    this.bold = bold;
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
        var terminalCharacter = getCharacter(character);
        put = painter.put(terminalCharacter, characterLocation);
        column = column + 1;
      }
      else {
        row = characterLocation.getRow() + 1;
        column = location.getColumn();
      }
    }
  }

  private TerminalCharacter getCharacter(char character) {
    if (bold) {
      return characterFactory.createCharacter(character, new CharacterDecoration(null, null, true));
    }
    return characterFactory.createCharacter(character);
  }

}
