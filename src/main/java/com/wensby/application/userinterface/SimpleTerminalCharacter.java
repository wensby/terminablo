package com.wensby.application.userinterface;

public interface SimpleTerminalCharacter extends TerminalCharacter {

  char getCharacter();

  @Override
  default int getRenderLength() {
    return 1;
  }
}
