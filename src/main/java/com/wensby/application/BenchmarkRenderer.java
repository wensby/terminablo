package com.wensby.application;

import com.wensby.application.userinterface.*;
import com.wensby.terminablo.scene.playscene.PartialBlockCharacterFactoryImpl;
import com.wensby.userinterface.LayerWriterImpl;
import com.wensby.util.UnitInterval;

import java.awt.*;

import static com.wensby.terminablo.userinterface.component.InterfaceLocation.at;
import static java.util.Objects.requireNonNull;

public class BenchmarkRenderer {

  private final TerminalLayerFactory layerFactory;
  private final TerminalCharacterFactory characterFactory;
  private final Benchmark benchmark;
  private final PartialBlockCharacterFactoryImpl partialBlockCharacterFactory = new PartialBlockCharacterFactoryImpl();

  public BenchmarkRenderer(
      TerminalLayerFactory layerFactory,
      TerminalCharacterFactory characterFactory,
      Benchmark benchmark
  ) {
    this.layerFactory = requireNonNull(layerFactory);
    this.characterFactory = requireNonNull(characterFactory);
    this.benchmark = requireNonNull(benchmark);
  }

  public TerminalLayer render(InterfaceSize size) {
    var layer = layerFactory.createBlankLayer(InterfaceSize.of(size.getWidth(), 4));
    var writer = new LayerWriterImpl(characterFactory, layer);
    var updateTimeMs = Long.toString(benchmark.getLastUpdateTime().toMillis());
    var renderTimeMs = Long.toString(benchmark.getLastRenderTime().toMillis());
    var tickTimeMs = Long.toString(benchmark.getLastTickTime().toMillis());
    renderGraph(size, layer);
    writer.write(
        "Update time ms: " + updateTimeMs + "\n" +
        "Render time ms: " + renderTimeMs + "\n" +
        "Tick time ms: " + tickTimeMs, at(0, 1));
    return layer;
  }

  private void renderGraph(InterfaceSize size, TerminalLayer layer) {
    var latestTickTimes = benchmark.getLatestTickTimes();
    for (int col = 0; col < size.getWidth() && col < latestTickTimes.size(); col++) {
      var unit = UnitInterval.truncate((double) latestTickTimes.get(col).toMillis() / 30d);
      layer.put(createCharacter(unit), at(size.getWidth() - col, 0));
    }
  }

  private TerminalCharacter createCharacter(UnitInterval unit) {
    if (unit.toIntRoundedDown(9) == 9) {
      return characterFactory.createCharacter(' ', Color.RED, null);
    }
    else {
      var partialBlockCharacter = this.partialBlockCharacterFactory.getPartialBlockCharacter(unit);
      return characterFactory.createCharacter(partialBlockCharacter, Color.WHITE, null);
    }
  }
}
