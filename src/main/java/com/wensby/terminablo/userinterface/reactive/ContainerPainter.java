package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.InterfaceLocation;
import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayerPainter;
import com.wensby.terminablo.userinterface.component.InterfaceComponent;

import java.util.List;
import java.util.Objects;

public class ContainerPainter {

  private final TerminalLayerPainter painter;
  private final List<Component> children;
  private final InterfaceSize availableSize;

  private int childrenHeight;

  public ContainerPainter(TerminalLayerPainter painter, List<Component> children) {
    this.painter = Objects.requireNonNull(painter);
    this.children = Objects.requireNonNull(children);
    availableSize = painter.getAvailableSize();
  }

  public void paint() {
    if (children.isEmpty()) {
      return;
    }
    childrenHeight = availableSize.getHeight() / children.size();
    for (int i = 0; i < children.size(); i++) {
      children.get(i).render(createChildPainter(i));
    }
  }

  private TerminalLayerPainter createChildPainter(int i) {
    var childSize = getChildPainterSize(i);
    var childPosition = InterfaceLocation.at(0, i * childrenHeight);
    return painter.createSubsectionPainter(childPosition, childSize);
  }

  private InterfaceSize getChildPainterSize(int i) {
    if (i == children.size() - 1) {
      return availableSize.minus(InterfaceSize.of(0, i * childrenHeight));
    }
    else {
      return InterfaceSize.of(availableSize.getWidth(), childrenHeight);
    }
  }
}
