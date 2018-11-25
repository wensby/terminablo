package com.wensby;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.userinterface.InterfaceRegion;

public interface LayerWriter {

  void write(String text, InterfaceLocation location);

  void write(InterfaceRegion interfaceRegion, String text);
}
