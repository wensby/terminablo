package com.wensby.terminablo.world;

public class MapLocation {

    private final int x;
    private final int y;

    private MapLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static MapLocation of(int x, int y) {
        return new MapLocation(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
