package com.wensby.userinterface;

import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import java.util.Objects;

public class InterfaceRegion {

  private final InterfaceLocation topLeftLocation;
  private final InterfaceSize size;

  public InterfaceRegion(
      InterfaceLocation topLeftLocation,
      InterfaceSize size
  ) {
    this.topLeftLocation = requireNonNull(topLeftLocation);
    this.size = requireNonNull(size);
  }
}
