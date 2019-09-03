package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.TerminalLayerPainter;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Grid implements InterfaceComponent {

  private final Map<String, InterfaceComponent> childrenByGridKey;
  private final String childLayout;
  private List<Integer> columnLayout;
  private List<Integer> rowLayout;

  public Grid(Map<String, InterfaceComponent> childrenByGridKey, String childLayout, List<Integer> columnLayout, List<Integer> rowLayout) {
    this.childrenByGridKey = Objects.requireNonNull(childrenByGridKey);
    this.childLayout = Objects.requireNonNull(childLayout);
    this.columnLayout = Objects.requireNonNull(columnLayout);
    this.rowLayout = Objects.requireNonNull(rowLayout);
  }

  @Override
  public void render(TerminalLayerPainter painter) {
    new GridPainter(painter, childrenByGridKey, childLayout, columnLayout, rowLayout).paint();
  }
}
