package com.wensby;

import static com.wensby.terminablo.userinterface.component.InterfaceLocation.atOrigin;
import static java.util.Objects.requireNonNull;

import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayer;
import com.wensby.userinterface.TerminalLayerFactory;

public class BenchmarkRendererImpl implements BenchmarkRenderer {

  private final TerminalLayerFactory layerFactory;
  private final TerminalCharacterFactory characterFactory;
  private final Benchmark benchmark;

  public BenchmarkRendererImpl(
      TerminalLayerFactory layerFactory,
      TerminalCharacterFactory characterFactory,
      Benchmark benchmark
  ) {
    this.layerFactory = requireNonNull(layerFactory);
    this.characterFactory = requireNonNull(characterFactory);
    this.benchmark = requireNonNull(benchmark);
  }

  @Override
  public TerminalLayer render(InterfaceSize size) {
    var layer = layerFactory.createBlankLayer(size);
    var writer = new LayerWriterImpl(characterFactory, layer);
    var updateTimeMs = Long.toString(benchmark.getLastUpdateTime().toMillis());
    writer.write("Update time ms: " + updateTimeMs, atOrigin());
    return layer;
  }
}
