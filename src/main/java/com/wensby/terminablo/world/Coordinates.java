package com.wensby.terminablo.world;

public class Coordinates {

  private final int x;
  private final int y;

  public Coordinates(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public static Coordinates of(int x, int y) {
    return new Coordinates(x, y);
  }

  public Coordinates plus(Coordinates coordinates) {
    return new Coordinates(x + coordinates.x, y + coordinates.y);
  }
}
