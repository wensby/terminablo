package com.wensby.application.userinterface;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;

public interface TerminalLayerPainter {

  InterfaceSize getAvailableSize();

  boolean put(TerminalCharacter character, InterfaceLocation location);

  void put(TerminalLayer layer, InterfaceLocation location);

  TerminalLayerPainter createSubsectionPainter(InterfaceLocation topLeft, InterfaceSize size);
}
