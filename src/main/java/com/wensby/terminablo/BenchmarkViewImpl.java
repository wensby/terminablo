package com.wensby.terminablo;

import com.wensby.terminalapp.BenchmarkRendererImpl;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.linux.TerminalCharacterFactoryImpl;
import com.wensby.userinterface.TerminalLayer;
import com.wensby.userinterface.TerminalLayerFactoryImpl;
import com.wensby.util.BenchmarkView;

public class BenchmarkViewImpl implements BenchmarkView {

  private final TerminalLayerFactoryImpl layerFactory;
  private final TerminalCharacterFactoryImpl characterFactory;
  private final BenchmarkModel benchmarkModel;

  public BenchmarkViewImpl(TerminalLayerFactoryImpl layerFactory,
      TerminalCharacterFactoryImpl characterFactory,
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
