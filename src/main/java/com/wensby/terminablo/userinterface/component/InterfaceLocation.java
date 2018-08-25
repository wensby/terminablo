package com.wensby.terminablo.userinterface.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InterfaceLocation {

    private static final Map<Integer, InterfaceLocation> cachedLocationsByHash = new HashMap<>();

    private final int row;
    private final int column;

    private InterfaceLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static InterfaceLocation of(int row, int column) {
        int hash = calculateHash(row, column);
        return cachedLocationsByHash.computeIfAbsent(hash, k -> new InterfaceLocation(row, column));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterfaceLocation that = (InterfaceLocation) o;
        return row == that.row &&
                column == that.column;
    }

    @Override
    public int hashCode() {
        return calculateHash(row, column);
    }

    private static int calculateHash(int row, int column) {
        return Objects.hash(row, column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
