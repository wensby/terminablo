package com.wensby.userinterface;

public class TerminalLayer {

    private final TerminalCharacter[][] characters;

    public TerminalLayer(TerminalCharacter[][] characters) {
        this.characters = characters;
    }

    public TerminalCharacter[][] getCharacters() {
        return characters;
    }

    public InterfaceSize getSize() {
        return new InterfaceSize(characters[0].length, characters.length);
    }

    public int getWidth() {
        return characters[0].length;
    }

    public int getHeight() {
        return characters.length;
    }

    public void put(TerminalLayer sphere, int y, int x) {
        for (int yi = 0; yi < sphere.getHeight(); yi++) {
            for (int xi = 0; xi < sphere.getWidth(); xi++) {
                characters[yi+y][xi+x] = sphere.characters[yi][xi];
            }
        }
    }
}
