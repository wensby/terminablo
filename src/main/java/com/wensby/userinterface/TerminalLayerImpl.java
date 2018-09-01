package com.wensby.userinterface;

public class TerminalLayerImpl implements TerminalLayer {

    private final TerminalCharacter[][] characters;

    public TerminalLayerImpl(TerminalCharacter[][] characters) {
        this.characters = characters;
    }

    @Override
    public TerminalCharacter[][] getCharacters() {
        return characters;
    }

    @Override
    public InterfaceSize getSize() {
        return new InterfaceSize(characters[0].length, characters.length);
    }

    @Override
    public void put(TerminalLayer layer, InterfacePosition target) {
        InterfaceSize size = layer.getSize();
        for (int row = 0; row < size.getHeight(); row++) {
            for (int column = 0; column < size.getWidth(); column++) {
                TerminalCharacter character = layer.getCharacter(InterfacePosition.of(column, row));
                characters[target.getRow() + row][target.getColumn() + column] = character;
            }
        }
    }

    @Override
    public void put(InterfacePosition position, TerminalCharacter character) {
        characters[position.getRow()][position.getColumn()] = character;
    }

    @Override
    public TerminalCharacter getCharacter(InterfacePosition interfacePosition) {
        return characters[interfacePosition.getRow()][interfacePosition.getColumn()];
    }
}
