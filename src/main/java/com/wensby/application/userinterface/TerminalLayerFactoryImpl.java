package com.wensby.application.userinterface;

public class TerminalLayerFactoryImpl implements TerminalLayerFactory {

  @Override
  public TerminalLayer createBlankLayer(InterfaceSize size) {
    var characters = new TerminalCharacter[size.getHeight()][size.getWidth()];
    return new TwoDimensionalCharacterArrayLayer(characters);
  }

}
