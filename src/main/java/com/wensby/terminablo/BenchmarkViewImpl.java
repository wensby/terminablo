package com.wensby.terminablo;

import com.wensby.application.BenchmarkRendererImpl;
import com.wensby.userinterface.*;
import com.wensby.userinterface.linux.TerminalCharacterFactoryImpl;
import com.wensby.util.BenchmarkView;

public class BenchmarkViewImpl implements BenchmarkView {

  private final TerminalLayerFactory layerFactory;
  private final TerminalCharacterFactory characterFactory;
  private final BenchmarkModel benchmarkModel;

  public BenchmarkViewImpl(TerminalLayerFactory layerFactory,
      TerminalCharacterFactory characterFactory,
      BenchmarkModel benchmarkModel) {
    this.layerFactory = layerFactory;
    this.characterFactory = characterFactory;
    this.benchmarkModel = benchmarkModel;
  }

  @Override
  public TerminalLayer render(InterfaceSize size) {
    final var benchmark = benchmarkModel.getBenchmark();
    final var renderer = new BenchmarkRendererImpl(layerFactory, characterFactory, benchmark);
    return renderer.render(size);
  }
}
