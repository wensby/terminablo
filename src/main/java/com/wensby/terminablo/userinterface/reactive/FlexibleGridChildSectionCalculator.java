package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.InterfaceLocation;
import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayerSection;
import com.wensby.terminablo.util.Integers;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class FlexibleGridChildSectionCalculator {

  private static final Logger LOGGER = Logger.getLogger(FlexibleGridChildSectionCalculator.class);

  private final InterfaceSize totalSize;
  private final List<List<String>> layoutRows;
  private final List<Integer> columnRatios;
  private final List<Integer> rowRatios;
  private final FlexibleGridChild child;

  public FlexibleGridChildSectionCalculator(InterfaceSize totalSize, List<Integer> columnRatios, List<Integer> rowRatios, List<List<String>> layoutRows, FlexibleGridChild child) {
    this.totalSize = totalSize;
    this.columnRatios = columnRatios;
    this.rowRatios = rowRatios;
    this.layoutRows = layoutRows;
    this.child = child;
  }

  public Optional<TerminalLayerSection> findChildLayerSection() {
    InterfaceLocation first = null;
    InterfaceLocation last = null;
    var ratioUnitSize = calculateRatioUnitSize();
    for (int r = 0; r < layoutRows.size(); r++) {
      var columns = layoutRows.get(r);
      for (int c = 0; c < columns.size(); c++) {
        var cellKey = columns.get(c);
        if (cellKey.equals(child.getKey())) {
          if (first == null) {
            first = InterfaceLocation.at(c, r);
          }
          last = InterfaceLocation.at(c, r);
        }
      }
    }
    if (first != null) {
      return Optional.of(getTerminalLayerSection(first, last, ratioUnitSize));
    }
    return Optional.empty();
  }

  private InterfaceSize calculateRatioUnitSize() {
    var ratioUnitWidth = totalSize.getWidth() / new Integers(columnRatios).sum();
    var ratioUnitHeight = totalSize.getHeight() / new Integers(rowRatios).sum();
    return InterfaceSize.of(ratioUnitWidth, ratioUnitHeight);
  }

  private TerminalLayerSection getTerminalLayerSection(InterfaceLocation first, InterfaceLocation last, InterfaceSize ratioUnitSize) {
    LOGGER.debug("Creating child layer subsection from " + first + " to " + last);
    var location = getSubsectionLocation(first, ratioUnitSize);
    var size = calculateSize(first, last, ratioUnitSize);
    LOGGER.debug("totalSize: " + size);
    return new TerminalLayerSection(location, size);
  }

  private InterfaceLocation getSubsectionLocation(InterfaceLocation first, InterfaceSize ratioUnitSize) {
    var beforeX = getWidthBetween(0, first.getColumn(), ratioUnitSize.getWidth());
    var beforeY = getHeightBetween(0, first.getRow(), ratioUnitSize.getHeight());
    return InterfaceLocation.at(beforeX, beforeY);
  }

  private InterfaceSize calculateSize(InterfaceLocation first, InterfaceLocation last, InterfaceSize ratioUnitSize) {
    int width;
    int height;
    if (last.getColumn() == layoutRows.get(0).size() - 1) {
      width = totalSize.getWidth() - getWidthBetween(0, first.getColumn(), ratioUnitSize.getWidth());
    }
    else {
      width = getWidthBetween(first.getColumn(), last.getColumn() + 1, ratioUnitSize.getWidth());
    }
    if (last.getRow() == layoutRows.size() - 1) {
      height = totalSize.getHeight() - getHeightBetween(0, first.getRow(), ratioUnitSize.getHeight());
    }
    else {
      height = getHeightBetween(first.getRow(), last.getRow() + 1, ratioUnitSize.getHeight());
    }
    return InterfaceSize.of(width, height);
  }

  private int getWidthBetween(int x1, int x2, int ratioUnitWidth) {
    return IntStream.range(x1, x2)
        .map(columnRatios::get)
        .map(columnRatio -> ratioUnitWidth * columnRatio)
        .sum();
  }

  private int getHeightBetween(int y1, int y2, int ratioUnitHeight) {
    return IntStream.range(y1, y2)
        .map(rowRatios::get)
        .map(rowRatio -> ratioUnitHeight * rowRatio)
        .sum();
  }
}
