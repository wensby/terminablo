package com.wensby.userinterface;

public interface TerminalLayer {

    TerminalCharacter[][] getCharacters();

    InterfaceSize getSize();

    void put(TerminalLayer layer, InterfacePosition interfacePosition);

    void put(InterfacePosition position, TerminalCharacter character);

    TerminalCharacter getCharacter(InterfacePosition position);
}
