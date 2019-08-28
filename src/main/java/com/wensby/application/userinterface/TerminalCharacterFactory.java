package com.wensby.application.userinterface;

public interface TerminalCharacterFactory {

  TerminalCharacter createCharacter(char character);

  TerminalCharacter createCharacter(char character, CharacterDecoration decoration);

  TerminalCharacter createCharacter(CharSequence character);
}
