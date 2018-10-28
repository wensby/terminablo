package com.wensby.terminablo;

import static java.util.Objects.requireNonNull;

import com.wensby.BenchmarkRendererImpl;
import com.wensby.TerminalLayerWriterImpl;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.LinuxTerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayer;
import com.wensby.userinterface.TerminalLayerFactoryImpl;
import com.wensby.util.BenchmarkView;

public class BenchmarkViewImpl implements BenchmarkView {

  private final TerminalLayerFactoryImpl layerFactory;
  private final LinuxTerminalCharacterFactory characterFactory;
  private final BenchmarkModel benchmarkModel;

  public BenchmarkViewImpl(TerminalLayerFactoryImpl layerFactory,
      LinuxTerminalCharacterFactory characterFactory,
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
