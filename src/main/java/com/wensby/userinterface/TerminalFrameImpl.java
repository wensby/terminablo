package com.wensby.userinterface;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;

public class TerminalFrameImpl implements TerminalFrame {

  private final TerminalLayer terminalLayer;

  public TerminalFrameImpl(TerminalLayer terminalLayer) {
    this.terminalLayer = terminalLayer;
  }

  @Override
  public TerminalCharacter[][] getCharacters() {
    return terminalLayer.getCharacters();
  }

  @Override
  public InterfaceSize getSize() {
    return terminalLayer.getSize();
  }

  @Override
  public void put(TerminalLayer layer, InterfaceLocation interfacePosition) {
    terminalLayer.put(layer, interfacePosition);
  }

  @Override
  public boolean put(InterfaceLocation position, TerminalCharacter character) {
    return terminalLayer.put(position, character);
  }

  @Override
  public TerminalCharacter getCharacter(InterfaceLocation interfacePosition) {
    return terminalLayer.getCharacter(interfacePosition);
  }
}
