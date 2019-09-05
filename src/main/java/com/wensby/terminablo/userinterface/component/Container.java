package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.InterfaceLocation;
import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayerPainter;

import java.util.List;
import java.util.Objects;

public class Container implements InterfaceComponent {

  private final List<InterfaceComponent> children;

  public Container(List<InterfaceComponent> children) {
    this.children = Objects.requireNonNull(children);
  }

  private static InterfaceSize combinedContentSize(InterfaceSize a, InterfaceSize b) {
    var width = Math.max(a.getWidth(), b.getWidth());
    var height = a.getHeight() + b.getHeight();
    return InterfaceSize.of(width, height);
  }

  @Override
  public void render(TerminalLayerPainter painter) {
    new ContainerPainter(painter, children).paint();
  }

  @Override
  public InterfaceSize contentSize() {
    return children.stream()
        .map(InterfaceComponent::contentSize)
        .reduce(Container::combinedContentSize)
        .orElse(InterfaceSize.of(0, 0));
  }
}

class ContainerPainter {

  private final TerminalLayerPainter painter;
  private final List<InterfaceComponent> children;
  private final InterfaceSize availableSize;

  private int childrenHeight;

  public ContainerPainter(TerminalLayerPainter painter, List<InterfaceComponent> children) {
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
