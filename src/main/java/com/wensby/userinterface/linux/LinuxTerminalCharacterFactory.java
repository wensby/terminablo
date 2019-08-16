package com.wensby.userinterface.linux;

import com.wensby.userinterface.*;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class LinuxTerminalCharacterFactory implements TerminalCharacterFactory {

  private static final Map<Character, SimpleTerminalCharacter> simpleTerminalCharacterByCharacter = new HashMap<>();

  @Override
  public TerminalCharacter createCharacter(char character) {
    return simpleTerminalCharacterByCharacter
        .computeIfAbsent(character, SimpleTerminalCharacterImpl::new);
  }

  @Override
  public TerminalCharacter createCharacter(char character, Color foregroundColor,
      Color backgroundColor) {
    return new ComplexTerminalCharacterImpl(String.valueOf(character),
        new CharacterDecoration(backgroundColor, foregroundColor));
  }

  @Override
  public TerminalCharacter createCharacter(CharSequence character) {
    return new ComplexTerminalCharacterImpl(character);
  }
}
