package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.Key;
import com.wensby.application.userinterface.TerminalLayer;
import com.wensby.terminablo.util.Integers;

import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toSet;

public class FlexibleGrid implements Component {

  private final Map<String, Component> childrenByGridKey;
  private final List<List<String>> layoutRows;
  private final List<Integer> columnRatios;
  private final List<Integer> rowRatios;

  FlexibleGrid(Map<String, Component> childrenByGridKey, List<List<String>> layoutRows, List<Integer> columnRatios, List<Integer> rowRatios) {
    this.childrenByGridKey = requireNonNull(childrenByGridKey);
    this.layoutRows = requireNonNull(layoutRows);
    this.columnRatios = requireNonNull(columnRatios);
    this.rowRatios = requireNonNull(rowRatios);
  }

  @Override
  public void render(TerminalLayer layer) {
    var children = childrenByGridKey.entrySet().stream()
        .map(entry -> new FlexibleGridChild(entry.getKey(), entry.getValue()))
        .collect(toSet());
    var renderer = new FlexibleGridRenderer(layer, children, layoutRows, columnRatios, rowRatios);
    renderer.render();
  }

  @Override
  public void sendKeys(List<Key> keys) {
    childrenByGridKey.values().forEach(child -> child.sendKeys(keys));
  }
}
