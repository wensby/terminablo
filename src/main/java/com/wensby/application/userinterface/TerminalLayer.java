package com.wensby.application.userinterface;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;

import java.util.List;

public interface TerminalLayer {

  TerminalCharacter[][] getCharacters();

  List<PositionedTerminalCharacter> getPositionedCharacters();

  InterfaceSize getSize();

  void put(TerminalLayer layer, InterfaceLocation interfacePosition);

  boolean put(TerminalCharacter character, InterfaceLocation position);

  TerminalCharacter getCharacter(InterfaceLocation position);
}
