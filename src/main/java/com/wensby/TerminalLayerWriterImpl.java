package com.wensby;

import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.userinterface.InterfaceRegion;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayer;

public class TerminalLayerWriterImpl implements TerminalLayerWriter {

  private final TerminalCharacterFactory characterFactory;
  private final TerminalLayer terminalLayer;

  public TerminalLayerWriterImpl(
      TerminalCharacterFactory characterFactory,
      TerminalLayer terminalLayer
  ) {
    this.characterFactory = requireNonNull(characterFactory);
    this.terminalLayer = requireNonNull(terminalLayer);
  }

  @Override
  public void write(String text, InterfaceLocation location) {
    requireNonNull(location);
    requireNonNull(text);
    boolean put = true;
    for (int i = 0; put && i < text.length(); i++) {
      var characterLocation = InterfaceLocation.of(location.getColumn() + i, location.getRow());
      var character = text.charAt(i);
      var terminalCharacter = characterFactory.createCharacter(character);
      put = terminalLayer.put(characterLocation, terminalCharacter);
    }
  }

  @Override
  public void write(InterfaceRegion interfaceRegion, String text) {

  }
}
