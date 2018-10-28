package com.wensby.userinterface;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;

public interface TerminalLayer {

  TerminalCharacter[][] getCharacters();

  InterfaceSize getSize();

  void put(TerminalLayer layer, InterfaceLocation interfacePosition);

  boolean put(InterfaceLocation position, TerminalCharacter character);

  TerminalCharacter getCharacter(InterfaceLocation position);
}
