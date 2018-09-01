package com.wensby.userinterface;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LinuxTerminalCharacterFactory implements TerminalCharacterFactory {

    private static final Map<Character, SimpleTerminalCharacter> simpleTerminalCharacterByCharacter = new HashMap<>();

    @Override
    public TerminalCharacter createCharacter(char character) {
        return simpleTerminalCharacterByCharacter.computeIfAbsent(character, SimpleTerminalCharacter::new);
    }

    @Override
    public TerminalCharacter createCharacter(char character, Color foregroundColor, Color backgroundColor) {
        return new LinuxDecorativeCharacter(String.valueOf(character), foregroundColor, backgroundColor);
    }

    @Override
    public TerminalCharacter createCharacter(CharSequence character) {
        return new LinuxDecorativeCharacter(character);
    }
}
