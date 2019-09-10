package com.wensby.application.userinterface;

import java.util.List;

public interface TerminalLayer {

  List<PositionedTerminalCharacter> getPositionedCharacters();

  InterfaceSize size();

  void put(TerminalLayer layer, InterfaceLocation location);

  boolean put(TerminalCharacter character, InterfaceLocation location);

  TerminalCharacter getCharacter(InterfaceLocation position);

  TerminalLayer getSubsection(InterfaceLocation topLeft, InterfaceSize size);
}
