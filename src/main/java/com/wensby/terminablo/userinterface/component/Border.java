package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.*;

import java.util.Objects;

import static com.wensby.application.userinterface.InterfaceLocation.at;
import static com.wensby.application.userinterface.InterfaceLocation.atOrigin;

public class Border implements InterfaceComponent {

  private final InterfaceComponent child;
  private final BorderStyle style;

  public Border(InterfaceComponent child, BorderStyle style) {
    this.child = Objects.requireNonNull(child);
    this.style = Objects.requireNonNull(style);
  }

  @Override
  public void render(TerminalLayer layer) {
    var size = layer.size();
    var topLeftLayer = style.getTopLeft().getLayer(size);
    var bottomLeftLayer = style.getBottomLeft().getLayer(size);
    var topRightLayer = style.getTopRight().getLayer(size);
    var bottomRightLayer = style.getBottomRight().getLayer(size);
    var topRightCornerWidth = topRightLayer.size().getWidth();
    var bottomLeftCornerHeight = bottomLeftLayer.size().getHeight();
    var topLeftCornerWidth = topLeftLayer.size().getWidth();
    var width = size.getWidth();
    var topStraightLength = width - topLeftCornerWidth - topRightCornerWidth;
    var topStraight = style.getTopStraight().getLayer(topStraightLength);
    var bottomLeftCornerWidth = bottomLeftLayer.size().getWidth();
    var bottomRightCornerWidth = bottomRightLayer.size().getWidth();
    var bottomStraightLength = width - bottomLeftCornerWidth - bottomRightCornerWidth;
    var bottomStraight = style.getBottomStraight().getLayer(bottomStraightLength);
    var height = size.getHeight();
    int topLeftCornerHeight = topLeftLayer.size().getHeight();
    int leftStraightLength = height - topLeftCornerHeight - bottomLeftCornerHeight;
    TerminalLayer leftStraight = style.getLeftStraight().getLayer(leftStraightLength);
    int topRightCornerHeight = topRightLayer.size().getHeight();
    int bottomRightCornerHeight = bottomRightLayer.size().getHeight();
    int rightStraightLength = height - topRightCornerHeight - bottomRightCornerHeight;
    TerminalLayer rightStraight = style.getRightStraight().getLayer(rightStraightLength);
    layer.put(topLeftLayer, atOrigin());
    layer.put(bottomLeftLayer, at(0, height - bottomLeftCornerHeight));
    layer.put(topRightLayer, at(width - topRightCornerWidth, 0));
    layer.put(bottomRightLayer, at(width - topRightCornerWidth, height - bottomLeftCornerHeight));
    layer.put(topStraight, at(topLeftCornerWidth, 0));
    layer.put(bottomStraight, at(topLeftCornerWidth, height - bottomStraight.size().getHeight()));
    layer.put(leftStraight, at(0, topLeftCornerHeight));
    layer.put(rightStraight, at(width - rightStraight.size().getWidth(), topRightCornerHeight));
    child.render(layer);
  }

  @Override
  public InterfaceSize contentSize() {
    return child.contentSize();
  }
}
