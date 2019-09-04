package com.wensby.terminablo.userinterface.component;

import java.util.ArrayList;
import java.util.List;

public class ContainerBuilder {

  private final List<InterfaceComponent> children = new ArrayList<>();

  public ContainerBuilder add(InterfaceComponent child) {
    children.add(child);
    return this;
  }

  public Container build() {
    return new Container(children);
  }
}
