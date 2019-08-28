package com.wensby.application;

import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayer;
import com.wensby.application.userinterface.TerminalLayerFactory;
import com.wensby.application.userinterface.BenchmarkView;

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
    final var renderer = new BenchmarkRenderer(layerFactory, characterFactory, benchmark);
    return renderer.render(size);
  }
}
