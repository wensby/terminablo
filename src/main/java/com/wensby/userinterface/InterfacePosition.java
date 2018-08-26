package com.wensby.userinterface;

public class InterfacePosition {

    private static final InterfacePosition ORIGIN = new InterfacePosition(0, 0);

    private final int column;
    private final int row;

    private InterfacePosition(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public static InterfacePosition atOrigin() {
        return ORIGIN;
    }

    public static InterfacePosition of(int column, int row) {
        return new InterfacePosition(column, row);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
