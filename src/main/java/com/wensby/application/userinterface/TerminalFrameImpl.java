package com.wensby.application.userinterface;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;

import java.util.List;

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
  public List<PositionedTerminalCharacter> getPositionedCharacters() {
    return layer.getPositionedCharacters();
  }

  @Override
  public InterfaceSize getSize() {
    return layer.getSize();
  }

  @Override
  public void put(TerminalLayer layer, InterfaceLocation location) {
    this.layer.put(layer, location);
  }

  @Override
  public boolean put(TerminalCharacter character, InterfaceLocation location) {
    return layer.put(character, location);
  }

  @Override
  public TerminalCharacter getCharacter(InterfaceLocation interfacePosition) {
    return layer.getCharacter(interfacePosition);
  }
}
