package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.InterfaceLocation;
import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayer;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.IntStream.range;

public class SimpleGridRenderer {
  private final List<? extends Component> children;
  private final int columns;
  private final TerminalLayer layer;

  private InterfaceSize childSize = null;

  public SimpleGridRenderer(List<? extends Component> children, int columns, TerminalLayer layer) {
    this.children = requireNonNull(children);
    this.columns = columns;
    this.layer = requireNonNull(layer);
  }

  public void render() {
    range(0, children.size()).forEach(this::renderChild);
  }

  private void renderChild(int i) {
    var layerSubsection = layer.getSubsection(childLocation(i), childSize());
    children.get(i).render(layerSubsection);
  }

  private InterfaceLocation childLocation(int i) {
    var childSize = childSize();
    var column = i % columns * childSize.getWidth();
    var row = (i / columns) * childSize.getHeight();
    return InterfaceLocation.at(column, row);
  }

  private InterfaceSize childSize() {
    if (childSize == null) {
      var rows = (int) (children.size() / (float) columns + 0.5f);
      childSize = InterfaceSize.of(layer.size().getWidth() / columns, layer.size().getHeight() / rows);
    }
    return childSize;
  }
}
