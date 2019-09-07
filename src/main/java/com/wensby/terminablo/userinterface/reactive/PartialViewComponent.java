package com.wensby.terminablo.userinterface.reactive;


import com.wensby.application.userinterface.*;

import java.util.List;
import java.util.Objects;

import static com.wensby.application.userinterface.InterfaceLocation.atOrigin;

public class PartialViewComponent implements Component {

  private final Component component;
  private final float childHeightRatio;
  private final float childWidthRatio;
  private final float leftRatio;
  private final float topRatio;

  public PartialViewComponent(Component component, float childHeightRatio, float childWidthRatio, float leftRatio, float topRatio) {
    this.component = Objects.requireNonNull(component);
    this.childHeightRatio = childHeightRatio;
    this.childWidthRatio = childWidthRatio;
    this.leftRatio = leftRatio;
    this.topRatio = topRatio;
  }

  @Override
  public void render(TerminalLayer layer) {
    var column = layer.getSize().getWidth() * leftRatio;
    var row = layer.getSize().getHeight() * topRatio;
    InterfaceLocation topLeft = InterfaceLocation.at((int)column, (int)row);
    var childWidth = layer.getSize().getWidth() * childWidthRatio;
    var childHeight = layer.getSize().getHeight() * childHeightRatio;
    InterfaceSize size = InterfaceSize.of((int)childWidth, (int)childHeight);
    component.render(layer.getSubsection(topLeft, size));
  }

  @Override
  public void sendKeys(List<Key> keys) {
    component.sendKeys(keys);
  }
}
