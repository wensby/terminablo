package com.wensby.userinterface;

public interface TerminalLayer {

    TerminalCharacter[][] getCharacters();

    InterfaceSize getSize();

    void put(TerminalLayer layer, InterfacePosition interfacePosition);

    TerminalCharacter getCharacter(InterfacePosition position);
}
