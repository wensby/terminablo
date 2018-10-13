package com.wensby.userinterface;

public class TerminalLayerFactoryImpl implements TerminalLayerFactory {

  @Override
  public TerminalLayer createTerminalLayer(InterfaceSize size) {
    TerminalCharacter[][] characters = new TerminalCharacter[size.getHeight()][size.getWidth()];
    return new TerminalLayerImpl(characters);
  }
}
