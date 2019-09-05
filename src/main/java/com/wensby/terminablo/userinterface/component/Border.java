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
  public void render(TerminalLayerPainter painter) {
    var size = painter.getAvailableSize();
    var topLeftLayer = style.getTopLeft().getLayer(size);
    var bottomLeftLayer = style.getBottomLeft().getLayer(size);
    var topRightLayer = style.getTopRight().getLayer(size);
    var bottomRightLayer = style.getBottomRight().getLayer(size);
    var topRightCornerWidth = topRightLayer.getSize().getWidth();
    var bottomLeftCornerHeight = bottomLeftLayer.getSize().getHeight();
    var topLeftCornerWidth = topLeftLayer.getSize().getWidth();
    var width = size.getWidth();
    var topStraightLength = width - topLeftCornerWidth - topRightCornerWidth;
    var topStraight = style.getTopStraight().getLayer(topStraightLength);
    var bottomLeftCornerWidth = bottomLeftLayer.getSize().getWidth();
    var bottomRightCornerWidth = bottomRightLayer.getSize().getWidth();
    var bottomStraightLength = width - bottomLeftCornerWidth - bottomRightCornerWidth;
    var bottomStraight = style.getBottomStraight().getLayer(bottomStraightLength);
    var height = size.getHeight();
    int topLeftCornerHeight = topLeftLayer.getSize().getHeight();
    int leftStraightLength = height - topLeftCornerHeight - bottomLeftCornerHeight;
    TerminalLayer leftStraight = style.getLeftStraight().getLayer(leftStraightLength);
    int topRightCornerHeight = topRightLayer.getSize().getHeight();
    int bottomRightCornerHeight = bottomRightLayer.getSize().getHeight();
    int rightStraightLength = height - topRightCornerHeight - bottomRightCornerHeight;
    TerminalLayer rightStraight = style.getRightStraight().getLayer(rightStraightLength);
    painter.put(topLeftLayer, atOrigin());
    painter.put(bottomLeftLayer, at(0, height - bottomLeftCornerHeight));
    painter.put(topRightLayer, at(width - topRightCornerWidth, 0));
    painter.put(bottomRightLayer, at(width - topRightCornerWidth, height - bottomLeftCornerHeight));
    painter.put(topStraight, at(topLeftCornerWidth, 0));
    painter.put(bottomStraight, at(topLeftCornerWidth, height - bottomStraight.getSize().getHeight()));
    painter.put(leftStraight, at(0, topLeftCornerHeight));
    painter.put(rightStraight, at(width - rightStraight.getSize().getWidth(), topRightCornerHeight));
    child.render(painter);
  }

  @Override
  public InterfaceSize contentSize() {
    return child.contentSize();
  }
}
