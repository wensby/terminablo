package com.wensby.application.userinterface;

public interface TerminalLayerPainter {

  InterfaceSize getAvailableSize();

  boolean put(TerminalCharacter character, InterfaceLocation location);

  void put(TerminalLayer layer, InterfaceLocation location);

  TerminalLayerPainter createSubsectionPainter(InterfaceLocation topLeft, InterfaceSize size);
}
