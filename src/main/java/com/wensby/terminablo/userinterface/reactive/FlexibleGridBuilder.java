package com.wensby.terminablo.userinterface.reactive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.List.copyOf;

public class FlexibleGridBuilder {

  private final Map<String, Component> childrenByGridKey;

  private List<Integer> columnRatios;
  private List<Integer> rowRatios;
  private List<List<String>> layoutRowByColumn;


  FlexibleGridBuilder() {
    childrenByGridKey = new HashMap<>();
    columnRatios = List.of();
    rowRatios = new ArrayList<>();
    layoutRowByColumn = new ArrayList<>();
  }

  public FlexibleGridBuilder withChild(String key, Component component) {
    childrenByGridKey.put(key, component);
    return this;
  }

  public FlexibleGridBuilder withColumnRatios(List<Integer> ratios) {
    columnRatios = copyOf(ratios);
    return this;
  }

  public FlexibleGridBuilder addRow(List<String> layoutRow, int ratio) {
    layoutRowByColumn.add(layoutRow);
    rowRatios.add(ratio);
    return this;
  }

  public FlexibleGrid build() {
    return new FlexibleGrid(childrenByGridKey, layoutRowByColumn, columnRatios, rowRatios);
  }
}
