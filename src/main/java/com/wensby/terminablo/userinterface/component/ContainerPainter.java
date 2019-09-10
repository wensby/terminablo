package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.InterfaceLocation;
import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayer;

import java.util.List;
import java.util.Objects;

public class ContainerPainter {

  private final TerminalLayer layer;
  private final List<InterfaceComponent> children;
  private final InterfaceSize availableSize;

  private int childrenHeight;

  public ContainerPainter(TerminalLayer layer, List<InterfaceComponent> children) {
    this.layer = Objects.requireNonNull(layer);
    this.children = Objects.requireNonNull(children);
    availableSize = layer.size();
  }

  public void paint() {
    if (children.isEmpty()) {
      return;
    }
    childrenHeight = availableSize.getHeight() / children.size();
    for (int i = 0; i < children.size(); i++) {
      children.get(i).render(createChildLayer(i));
    }
  }

  private TerminalLayer createChildLayer(int i) {
    var childSize = getChildLayerSize(i);
    var childPosition = InterfaceLocation.at(0, i * childrenHeight);
    return layer.getSubsection(childPosition, childSize);
  }

  private InterfaceSize getChildLayerSize(int i) {
    if (i == children.size() - 1) {
      return availableSize.minus(InterfaceSize.of(0, i * childrenHeight));
    }
    else {
      return InterfaceSize.of(availableSize.getWidth(), childrenHeight);
    }
  }
}
