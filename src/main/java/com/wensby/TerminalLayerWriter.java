package com.wensby;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.userinterface.InterfaceRegion;

public interface TerminalLayerWriter {

  void write(String text, InterfaceLocation location);

  void write(InterfaceRegion interfaceRegion, String text);
}
