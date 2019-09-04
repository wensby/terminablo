package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.InterfaceLocation;
import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayerPainter;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GridPainter {

  private final TerminalLayerPainter painter;
  private final Map<String, InterfaceComponent> childrenByGridKey;
  private final String childLayout;
  private final List<Integer> columnLayout;
  private final List<Integer> rowLayout;
  private final InterfaceSize totalSize;

  private Set<String> paintedGridKeys;

  public GridPainter(TerminalLayerPainter painter, Map<String, InterfaceComponent> childrenByGridKey, String childLayout, List<Integer> columnLayout, List<Integer> rowLayout) {
    this.painter = painter;
    this.childrenByGridKey = childrenByGridKey;
    this.childLayout = childLayout;
    this.columnLayout = columnLayout;
    this.rowLayout = rowLayout;
    this.totalSize = InterfaceSize.of(
        columnLayout.stream().mapToInt(Integer::intValue).sum(),
        rowLayout.stream().mapToInt(Integer::intValue).sum()
    );
    Logger.getLogger(this.getClass()).debug("Total Size:" + totalSize);
  }

  public void paint() {
    paintedGridKeys = new HashSet<>();
    Stream.generate(this::unpaintedKey)
        .takeWhile(Optional::isPresent)
        .map(Optional::get)
        .forEach(this::paintKey);
  }

  private void paintKey(String key) {
    getPainter(key).ifPresent(painter -> childrenByGridKey.get(key).render(painter));
    paintedGridKeys.add(key);
  }

  private Optional<TerminalLayerPainter> getPainter(String key) {
    var firstGridItem = getFirstGridItem(key);
    if (firstGridItem.isPresent()) {
      var lastGridItem = getLastGridItem(key);
      return Optional.of(getPainter(firstGridItem.get(), lastGridItem));
    }
    return Optional.empty();
  }

  private TerminalLayerPainter getPainter(InterfaceLocation first, InterfaceLocation last) {
    Logger.getLogger(this.getClass()).debug("first:" + first);
    Logger.getLogger(this.getClass()).debug("last:" + last);
    var location = getSubsectionLocation(first);
    var size = getSubsectionSize(first, last);
    Logger.getLogger(this.getClass()).debug(location);
    Logger.getLogger(this.getClass()).debug(size);
    return painter.createSubsectionPainter(location, size);
  }

  private InterfaceLocation getSubsectionLocation(InterfaceLocation first) {
    var beforeX = getWidthBetween(0, first.getColumn());
    var beforeY = getHeightBetween(0, first.getRow());
    return InterfaceLocation.at(beforeX, beforeY);
  }

  private InterfaceSize getSubsectionSize(InterfaceLocation first, InterfaceLocation last) {
    int width;
    int height;
    if (last.getColumn() == columnLayout.size() - 1) {
      width = painter.getAvailableSize().getWidth() - getWidthBetween(0, first.getColumn());
    }
    else {
      width = getWidthBetween(first.getColumn(), last.getColumn() + 1);
    }
    if (last.getRow() == rowLayout.size() - 1) {
      height = painter.getAvailableSize().getHeight() - getHeightBetween(0, first.getRow());
    }
    else {
      height = getHeightBetween(first.getRow(), last.getRow() + 1);
    }
    return InterfaceSize.of(width, height);
  }

  private int getWidthBetween(int x1, int x2) {
    return IntStream.range(x1, x2)
        .map(columnLayout::get)
        .map(c -> painter.getAvailableSize().getWidth() * c / totalSize.getWidth())
        .sum();
  }

  private int getHeightBetween(int y1, int y2) {
    return IntStream.range(y1, y2)
        .map(rowLayout::get)
        .map(c -> painter.getAvailableSize().getHeight() * c / totalSize.getHeight())
        .sum();
  }

  private Optional<InterfaceLocation> getFirstGridItem(String key) {
    var rows = childLayout.split("\n");
    for (int row = 0; row < rows.length; row++) {
      var columns = rows[row].split(" ");
      for (int column = 0; column < columns.length; column++) {
        if (columns[column].equals(key)) {
          return Optional.of(InterfaceLocation.at(column, row));
        }
      }
    }
    return Optional.empty();
  }

  private InterfaceLocation getLastGridItem(String key) {
    var lastLocation = (InterfaceLocation) null;
    var rows = childLayout.split("\n");
    for (int row = 0; row < rows.length; row++) {
      var columns = rows[row].split(" ");
      for (int column = 0; column < columns.length; column++) {
        if (columns[column].equals(key)) {
          lastLocation = InterfaceLocation.at(column, row);
        }
      }
    }
    return lastLocation;
  }

  private Optional<String> unpaintedKey() {
    return Stream.of(childLayout.split("\n"))
        .map(row -> Stream.of(row.split(" ")))
        .flatMap(rowKeysStream -> rowKeysStream)
        .filter(childrenByGridKey.keySet()::contains)
        .filter(key -> !paintedGridKeys.contains(key))
        .findFirst();
  }
}
