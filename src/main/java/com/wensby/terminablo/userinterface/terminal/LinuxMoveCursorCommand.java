package com.wensby.terminablo.userinterface.terminal;

public class LinuxMoveCursorCommand implements MoveCursorCommand {

    private final TerminalCoordinates coordinates;

    public LinuxMoveCursorCommand(TerminalCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public TerminalCoordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "LinuxMoveCursorCommand{" +
                "coordinates=" + coordinates +
                '}';
    }

    @Override
    public String toRenderString() {
        char escapeCode = 0x1B;
        int row = coordinates.getRow() + 1;
        int column = coordinates.getColumn() + 1;
        return String.format("%c[%d;%df", escapeCode, row, column);
    }
}
