package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.Key;
import com.wensby.application.userinterface.TerminalLayer;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FlexibleGrid implements Component {

  private final Map<String, Component> childrenByGridKey;
  private final List<List<String>> layoutRowByColumn;
  private final List<Integer> columnLayout;
  private final List<Integer> rowLayout;

  FlexibleGrid(Map<String, Component> childrenByGridKey, List<List<String>> layoutRowByColumn, List<Integer> columnLayout, List<Integer> rowLayout) {
    this.childrenByGridKey = Objects.requireNonNull(childrenByGridKey);
    this.layoutRowByColumn = Objects.requireNonNull(layoutRowByColumn);
    this.columnLayout = Objects.requireNonNull(columnLayout);
    this.rowLayout = Objects.requireNonNull(rowLayout);
  }

  @Override
  public void render(TerminalLayer layer) {
    var renderer = new FlexibleGridRenderer(layer, childrenByGridKey, layoutRowByColumn, columnLayout, rowLayout);
    renderer.render();
  }

  @Override
  public void sendKeys(List<Key> keys) {
    childrenByGridKey.values().forEach(child -> child.sendKeys(keys));
  }
}
