package com.wensby.userinterface;

import java.awt.*;

public interface TerminalCharacterFactory {

    TerminalCharacter createCharacter(char character);

    TerminalCharacter createCharacter(char character, Color foregroundColor, Color backgroundColor);

    TerminalCharacter createCharacter(CharSequence character);
}
