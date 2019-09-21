package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.InterfaceLocation;
import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayerSection;
import com.wensby.terminablo.util.Integers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class FlexibleGridChildSectionCalculator {

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
    return findChildCoordinates().map(this::createSection);
  }

  private Optional<List<Coordinates>> findChildCoordinates() {
    var childCoordinates = getChildCoordinates();
    return childCoordinates.isEmpty() ? Optional.empty() : Optional.of(childCoordinates);
  }

  private TerminalLayerSection createSection(List<Coordinates> childCoordinates) {
    var topLeft = topLeft(childCoordinates.get(0));
    var bottomRight = bottomRight(childCoordinates.get(childCoordinates.size() - 1));
    var size = InterfaceSize.between(topLeft, bottomRight);
    return new TerminalLayerSection(topLeft, size);
  }

  private List<Coordinates> getChildCoordinates() {
    return coordinatesKeyPairsStream()
        .filter(pair -> pair.key().equals(child.getKey()))
        .map(CoordinatesKeyPair::coordinates)
        .collect(toList());
  }

  private InterfaceLocation topLeft(Coordinates coordinates) {
    return location(coordinates.x(), coordinates.y());
  }

  private InterfaceLocation bottomRight(Coordinates coordinates) {
    return location(coordinates.x() + 1, coordinates.y() + 1);
  }

  private Stream<CoordinatesKeyPair> coordinatesKeyPairsStream() {
    return range(0, layoutRows.size())
        .mapToObj(this::rowCoordinatesKeyPairsStream)
        .flatMap(stream -> stream);
  }

  private InterfaceLocation location(int x1, int y1) {
    var ratioUnitSize = calculateRatioUnitSize();
    var column = (int) (getLengthBetween(0, x1, ratioUnitSize.widthRatio(), columnRatios) + 0.5);
    var row = (int) (getLengthBetween(0, y1, ratioUnitSize.heightRatio(), rowRatios) + 0.5);
    return InterfaceLocation.at(column, row);
  }

  private Stream<CoordinatesKeyPair> rowCoordinatesKeyPairsStream(int row) {
    var columns = layoutRows.get(row);
    return range(0, columns.size())
        .mapToObj(col -> new CoordinatesKeyPair(new Coordinates(col, row), columns.get(col)));
  }

  private RatioUnitSize calculateRatioUnitSize() {
    var ratioUnitWidth = (float) totalSize.getWidth() / (float) new Integers(columnRatios).sum();
    var ratioUnitHeight = (float) totalSize.getHeight() / (float) new Integers(rowRatios).sum();
    return new RatioUnitSize(ratioUnitWidth, ratioUnitHeight);
  }

  private double getLengthBetween(int a, int b, float ratio, List<Integer> cellRatios) {
    return range(a, b)
        .map(cellRatios::get)
        .mapToDouble(cellRatio -> ratio * cellRatio)
        .sum();
  }

  private static class CoordinatesKeyPair {

    private final Coordinates coordinates;
    private final String key;

    private CoordinatesKeyPair(Coordinates coordinates, String key) {
      this.coordinates = coordinates;
      this.key = key;
    }

    public Coordinates coordinates() {
      return coordinates;
    }

    public String key() {
      return key;
    }
  }

  private static class Coordinates {

    private final int x;
    private final int y;

    private Coordinates(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int x() {
      return x;
    }

    public int y() {
      return y;
    }
  }

  private static class RatioUnitSize {

    private final float width;
    private final float height;

    public RatioUnitSize(float width, float height) {
      this.width = width;
      this.height = height;
    }

    public float widthRatio() {
      return width;
    }

    public float heightRatio() {
      return height;
    }
  }
}