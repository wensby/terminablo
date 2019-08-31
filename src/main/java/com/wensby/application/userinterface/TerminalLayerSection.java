package com.wensby.application.userinterface;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;

import java.util.Objects;

public class TerminalLayerSection {

  private final InterfaceLocation topLeft;
  private final InterfaceSize size;

  public TerminalLayerSection(InterfaceLocation topLeft, InterfaceSize size) {
    this.topLeft = Objects.requireNonNull(topLeft);
    this.size = Objects.requireNonNull(size);
  }

  public InterfaceSize getSize() {
    return size;
  }

  public TerminalLayerSection createSubsection(InterfaceLocation topLeft, InterfaceSize size) {
    if (isInvalidSubsection(topLeft, size)) {
      throw new RuntimeException("Invalid subsection");
    }
    return new TerminalLayerSection(this.topLeft.plus(topLeft), size);
  }

  private boolean isInvalidSubsection(InterfaceLocation topLeft, InterfaceSize size) {
    return topLeft.getRow() < 0
        || topLeft.getColumn() < 0
        || (size.getHeight() + topLeft.getRow() > this.size.getHeight())
        || (size.getWidth() + topLeft.getColumn() > this.size.getWidth());
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
}
