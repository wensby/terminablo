package com.wensby.application.userinterface;

import java.awt.*;

public interface TerminalLayerFactory {

  TerminalLayer createBlankLayer(InterfaceSize size);
  
  TerminalLayer createColoredLayer(InterfaceSize size, Color color);
}
