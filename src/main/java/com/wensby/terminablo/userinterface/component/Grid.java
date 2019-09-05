package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayerPainter;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Grid implements InterfaceComponent {

  private final Map<String, InterfaceComponent> childrenByGridKey;
  private final String childLayout;
  private final List<Integer> columnLayout;
  private final List<Integer> rowLayout;

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

  @Override
  public InterfaceSize contentSize() {
    return InterfaceSize.ZERO;
  }
}
