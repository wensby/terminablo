package com.wensby.terminablo.userinterface.reactive;


import com.wensby.application.userinterface.*;

import java.util.List;
import java.util.Objects;

public class PartialViewComponent implements Component {

  private final Component component;
  private final float childHeightRatio;
  private final float childWidthRatio;
  private final float leftRatio;
  private final float topRatio;

  public PartialViewComponent(Component component, float childHeightRatio, float childWidthRatio, float topRatio, float leftRatio) {
    this.component = Objects.requireNonNull(component);
    this.childHeightRatio = childHeightRatio;
    this.childWidthRatio = childWidthRatio;
    this.topRatio = topRatio;
    this.leftRatio = leftRatio;
  }

  @Override
  public void render(TerminalLayer layer) {
    InterfaceLocation topLeft = getTopLeft(layer);
    InterfaceSize size = getSize(layer);
    component.render(layer.getSubsection(topLeft, size));
  }

  private InterfaceLocation getTopLeft(TerminalLayer layer) {
    var column = (int)(layer.getSize().getWidth() * leftRatio);
    var row = (int)((float)layer.getSize().getHeight() * topRatio);
    return InterfaceLocation.at(column, row);
  }

  private InterfaceSize getSize(TerminalLayer layer) {
    var childWidth = layer.getSize().getWidth() * childWidthRatio;
    var childHeight = layer.getSize().getHeight() * childHeightRatio;
    return InterfaceSize.of((int)childWidth, (int)childHeight);
  }

  @Override
  public void sendKeys(List<Key> keys) {
    component.sendKeys(keys);
  }
}
