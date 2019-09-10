package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.InterfaceLocation;
import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.Key;
import com.wensby.application.userinterface.TerminalLayer;

import java.util.List;
import java.util.Objects;

public class SimpleGrid implements Component {

  private final List<? extends Component> children;
  private final int columns;

  public SimpleGrid(List<? extends Component> children, int columns) {
    this.children = Objects.requireNonNull(children);
    this.columns = columns;
  }

  @Override
  public void render(TerminalLayer layer) {
    var rows = (int)(children.size() / (float)columns + 0.5f);
    var itemSize = InterfaceSize.of(layer.size().getWidth() / columns, layer.size().getHeight() / rows);
    for (int i = 0; i < children.size(); i++) {
      var row = i / columns;
      var column = i % columns;
      var topLeft = InterfaceLocation.at(column * itemSize.getWidth(), row * itemSize.getHeight());
      children.get(i).render(layer.getSubsection(topLeft, itemSize));
    }
  }

  @Override
  public void sendKeys(List<Key> keys) {
    children.forEach(child -> child.sendKeys(keys));
  }
}
