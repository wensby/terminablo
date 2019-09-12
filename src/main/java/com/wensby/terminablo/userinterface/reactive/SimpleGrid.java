package com.wensby.terminablo.userinterface.reactive;

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
    new SimpleGridRenderer(children, columns, layer).render();
  }

  @Override
  public void sendKeys(List<Key> keys) {
    children.forEach(child -> child.sendKeys(keys));
  }
}
