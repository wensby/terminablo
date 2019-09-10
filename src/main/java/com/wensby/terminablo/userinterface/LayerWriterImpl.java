package com.wensby.terminablo.userinterface;

import static java.util.Objects.requireNonNull;

import com.wensby.application.userinterface.*;

import java.util.Optional;

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
      var characterLocation = InterfaceLocation.at(column, row);
      var character = text.charAt(i);
      if (character != '\n') {
        var terminalCharacter = getCharacter(character);
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
  public void write(DecoratedText text, InterfaceLocation location) {
    requireNonNull(location);
    requireNonNull(text);
    var row = location.getRow();
    var column = location.getColumn();
    boolean put = true;
    for (int i = 0; put && i < text.length(); i++) {
      var characterLocation = InterfaceLocation.at(column, row);
      var character = text.charAt(i);
      if (character != '\n') {
        var decoration = text.getDecoration(i);
        var terminalCharacter = decoration.isPresent() ? characterFactory.createCharacter(character, decoration.get()) : characterFactory.createCharacter(character);
        put = layer.put(terminalCharacter, characterLocation);
        column = column + 1;
      }
      else {
        row = characterLocation.getRow() + 1;
        column = location.getColumn();
      }
    }
  }

  private TerminalCharacter getCharacter(char character) {
    return characterFactory.createCharacter(character);
  }

}
