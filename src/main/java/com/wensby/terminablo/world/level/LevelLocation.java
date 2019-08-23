package com.wensby.terminablo.world.level;

import java.util.Objects;

public class LevelLocation {

  public static final LevelLocation ZERO = LevelLocation.of(0, 0);

  private final int x;
  private final int y;

  private LevelLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public static LevelLocation of(int x, int y) {
    return new LevelLocation(x, y);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LevelLocation that = (LevelLocation) o;
    return x == that.x &&
        y == that.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  public LevelLocation plus(LevelLocation location) {
    return of(x + location.x, y + location.y);
  }
}
