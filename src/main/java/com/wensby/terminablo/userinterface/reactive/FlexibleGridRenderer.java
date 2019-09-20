package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.TerminalLayer;
import org.apache.log4j.Logger;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

public class FlexibleGridRenderer {

  private static final Logger LOGGER = Logger.getLogger(FlexibleGridRenderer.class);

  private final TerminalLayer layer;
  private final Set<FlexibleGridChild> children;
  private final List<List<String>> layoutRows;
  private final List<Integer> columnRatios;
  private final List<Integer> rowRatios;

  public FlexibleGridRenderer(TerminalLayer layer, Set<FlexibleGridChild> children, List<List<String>> layoutRows, List<Integer> columnRatios, List<Integer> rowRatios) {
    this.layer = requireNonNull(layer);
    this.children = requireNonNull(children);
    this.layoutRows = requireNonNull(layoutRows);
    this.columnRatios = requireNonNull(columnRatios);
    this.rowRatios = requireNonNull(rowRatios);
  }

  public void render() {
    LOGGER.debug("Rendering Flexible Grid. Children: " + this.children.stream().map(FlexibleGridChild::getKey).collect(joining(", ")));
    children.forEach(this::renderChild);
  }

  private void renderChild(FlexibleGridChild child) {
    var sectionCalculator = new FlexibleGridChildSectionCalculator(layer.size(), columnRatios, rowRatios, layoutRows, child);
    sectionCalculator.findChildLayerSection().map(layer::getSubsection).ifPresent(childLayer -> renderChild(child, childLayer));
  }

  private void renderChild(FlexibleGridChild child, TerminalLayer childLayer) {
    LOGGER.debug("Rendering Flexible Grid Child " + child.getKey() + ". Size: " + childLayer.size());
    child.getComponent().render(childLayer);
  }

}
