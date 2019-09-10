package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.Key;
import com.wensby.application.userinterface.TerminalLayer;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FlexibleGrid implements Component {

  private final Map<String, Component> childrenByGridKey;
  private final String childLayout;
  private final List<Integer> columnLayout;
  private final List<Integer> rowLayout;

  public FlexibleGrid(Map<String, Component> childrenByGridKey, String childLayout, List<Integer> columnLayout, List<Integer> rowLayout) {
    this.childrenByGridKey = Objects.requireNonNull(childrenByGridKey);
    this.childLayout = Objects.requireNonNull(childLayout);
    this.columnLayout = Objects.requireNonNull(columnLayout);
    this.rowLayout = Objects.requireNonNull(rowLayout);
  }

  @Override
  public void render(TerminalLayer layer) {
    new GridPainter(layer, childrenByGridKey, childLayout, columnLayout, rowLayout).paint();
  }

  @Override
  public void sendKeys(List<Key> keys) {
    childrenByGridKey.values().forEach(child -> child.sendKeys(keys));
  }
}
