package com.wensby.application.userinterface;

public class TerminalLayerFactoryImpl implements TerminalLayerFactory {

  @Override
  public TerminalLayer createBlankLayer(InterfaceSize size) {
    return new TwoDimensionalCharacterArrayLayer(size);
  }

}
