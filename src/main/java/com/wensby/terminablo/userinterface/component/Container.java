package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayerPainter;

import java.util.List;
import java.util.Objects;

public class Container implements InterfaceComponent {

  private final List<InterfaceComponent> children;

  public Container(List<InterfaceComponent> children) {
    this.children = Objects.requireNonNull(children);
  }

  @Override
  public void render(TerminalLayerPainter painter) {
    if (!children.isEmpty()) {
      var height = painter.getAvailableSize().getHeight();
      var childrenHeight = height / children.size();
      for (int i = 0; i < children.size(); i++) {
        var child = children.get(i);
        InterfaceSize size;
        if (i == children.size() - 1) {
          size = painter.getAvailableSize().minus(InterfaceSize.of(0, i * childrenHeight));
        }
        else {
          size = InterfaceSize.of(painter.getAvailableSize().getWidth(), childrenHeight);
        }
        child.render(painter.createSubsectionPainter(InterfaceLocation.at(0, i * childrenHeight), size));
      }
    }
  }
}
