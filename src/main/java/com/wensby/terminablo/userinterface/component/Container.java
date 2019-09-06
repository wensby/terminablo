package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayer;

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
  public void render(TerminalLayer layer) {
    new ContainerPainter(layer, children).paint();
  }

  @Override
  public InterfaceSize contentSize() {
    return children.stream()
        .map(InterfaceComponent::contentSize)
        .reduce(Container::combinedContentSize)
        .orElse(InterfaceSize.of(0, 0));
  }
}

