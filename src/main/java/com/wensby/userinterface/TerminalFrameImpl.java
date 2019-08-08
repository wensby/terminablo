package com.wensby.userinterface;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;

public class TerminalFrameImpl implements TerminalFrame {

  private final TerminalLayer layer;

  public TerminalFrameImpl(TerminalLayer layer) {
    this.layer = layer;
  }

  @Override
  public TerminalCharacter[][] getCharacters() {
    return layer.getCharacters();
  }

  @Override
  public InterfaceSize getSize() {
    return layer.getSize();
  }

  @Override
  public void put(TerminalLayer layer, InterfaceLocation interfacePosition) {
    this.layer.put(layer, interfacePosition);
  }

  @Override
  public boolean put(TerminalCharacter character, InterfaceLocation position) {
    return layer.put(character, position);
  }

  @Override
  public TerminalCharacter getCharacter(InterfaceLocation interfacePosition) {
    return layer.getCharacter(interfacePosition);
  }
}