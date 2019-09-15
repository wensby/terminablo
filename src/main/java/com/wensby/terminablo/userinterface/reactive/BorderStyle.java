package com.wensby.terminablo.userinterface.reactive;

import java.util.Objects;

public class BorderStyle {

  private final BorderCorner topLeft;
  private final BorderCorner topRight;
  private final BorderCorner bottomLeft;
  private final BorderCorner bottomRight;
  private final BorderStraight topStraight;
  private final BorderStraight bottomStraight;
  private final BorderStraight leftStraight;
  private final BorderStraight rightStraight;

  public BorderStyle(
      BorderCorner topLeft,
      BorderCorner topRight,
      BorderCorner bottomLeft,
      BorderCorner bottomRight,
      BorderStraight topStraight,
      BorderStraight bottomStraight,
      BorderStraight leftStraight,
      BorderStraight rightStraight) {
    this.topLeft = Objects.requireNonNull(topLeft);
    this.topRight = Objects.requireNonNull(topRight);
    this.bottomLeft = Objects.requireNonNull(bottomLeft);
    this.bottomRight = Objects.requireNonNull(bottomRight);
    this.topStraight = Objects.requireNonNull(topStraight);
    this.bottomStraight = Objects.requireNonNull(bottomStraight);
    this.leftStraight = Objects.requireNonNull(leftStraight);
    this.rightStraight = Objects.requireNonNull(rightStraight);
  }

  public BorderCorner getTopLeft() {
    return topLeft;
  }

  public BorderCorner getTopRight() {
    return topRight;
  }

  public BorderCorner getBottomLeft() {
    return bottomLeft;
  }

  public BorderCorner getBottomRight() {
    return bottomRight;
  }

  public BorderStraight getTopStraight() {
    return topStraight;
  }

  public BorderStraight getBottomStraight() {
    return bottomStraight;
  }

  public BorderStraight getLeftStraight() {
    return leftStraight;
  }

  public BorderStraight getRightStraight() {
    return rightStraight;
  }
}
