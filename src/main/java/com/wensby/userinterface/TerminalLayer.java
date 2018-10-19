package com.wensby.userinterface;

public interface TerminalLayer {

  TerminalCharacter[][] getCharacters();

  InterfaceSize getSize();

  void put(InterfacePosition interfacePosition, TerminalLayer layer);

  void put(InterfacePosition position, TerminalCharacter character);

  TerminalCharacter getCharacter(InterfacePosition position);
}
