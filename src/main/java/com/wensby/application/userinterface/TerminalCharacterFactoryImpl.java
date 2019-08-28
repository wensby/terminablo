package com.wensby.application.userinterface;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TerminalCharacterFactoryImpl implements TerminalCharacterFactory {

  private static final Map<Character, SimpleTerminalCharacter> simpleTerminalCharacterByCharacter = new HashMap<>();

  @Override
  public TerminalCharacter createCharacter(char character) {
    return simpleTerminalCharacterByCharacter
        .computeIfAbsent(character, SimpleTerminalCharacterImpl::new);
  }

  @Override
  public TerminalCharacter createCharacter(char character, CharacterDecoration decoration) {
    return new ComplexTerminalCharacterImpl(String.valueOf(character), decoration);
  }

  @Override
  public TerminalCharacter createCharacter(CharSequence character) {
    return new ComplexTerminalCharacterImpl(character);
  }
}
