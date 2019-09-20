package com.wensby.application.userinterface;

import java.util.Objects;

public class TerminalLayerSection {

  private final InterfaceLocation topLeft;
  private final InterfaceSize size;

  public TerminalLayerSection(InterfaceLocation topLeft, InterfaceSize size) {
    this.topLeft = Objects.requireNonNull(topLeft);
    this.size = Objects.requireNonNull(size);
  }

  public InterfaceLocation getTopLeft() {
    return topLeft;
  }

  public InterfaceSize getSize() {
    return size;
  }

  public TerminalLayerSection createSection(InterfaceLocation topLeft, InterfaceSize size) {
    return new TerminalLayerSection(topLeft, size);
  }

  public boolean containsRelativeLocation(InterfaceLocation location) {
    return location.getRow() < size.getHeight()
        && location.getColumn() < size.getWidth()
        && location.getRow() >= 0
        && location.getColumn() >= 0;
  }

  public InterfaceLocation getAbsoluteLocation(InterfaceLocation location) {
    return location.plus(topLeft);
  }

  @Override
  public String toString() {
    return "TerminalLayerSection{" +
        "topLeft=" + topLeft +
        ", size=" + size +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TerminalLayerSection that = (TerminalLayerSection) o;
    return Objects.equals(topLeft, that.topLeft) &&
        Objects.equals(size, that.size);
  }

  @Override
  public int hashCode() {
    return Objects.hash(topLeft, size);
  }
}
