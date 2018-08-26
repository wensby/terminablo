package com.wensby.userinterface;

public class TerminalFrameImpl implements TerminalFrame {

    private final TerminalLayer terminalLayer;

    public TerminalFrameImpl(TerminalLayer terminalLayer) {
        this.terminalLayer = terminalLayer;
    }

    @Override
    public TerminalCharacter[][] getCharacters() {
        return terminalLayer.getCharacters();
    }

    @Override
    public InterfaceSize getSize() {
        return terminalLayer.getSize();
    }

    @Override
    public void put(TerminalLayer layer, InterfacePosition interfacePosition) {
        terminalLayer.put(layer, interfacePosition);
    }

    @Override
    public TerminalCharacter getCharacter(InterfacePosition interfacePosition) {
        return terminalLayer.getCharacter(interfacePosition);
    }
}
