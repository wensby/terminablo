package com.wensby.userinterface;

import java.awt.Color;

public interface TerminalLayerFactory {

  TerminalLayer createBlankLayer(InterfaceSize size);
  
  TerminalLayer createColoredLayer(InterfaceSize size, Color color);
}
