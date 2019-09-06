package com.wensby.application;

import com.wensby.application.userinterface.*;

public class BenchmarkViewImpl implements BenchmarkView {

  private final TerminalCharacterFactory characterFactory;
  private final BenchmarkModel benchmarkModel;

  public BenchmarkViewImpl(TerminalCharacterFactory characterFactory,
      BenchmarkModel benchmarkModel) {
    this.characterFactory = characterFactory;
    this.benchmarkModel = benchmarkModel;
  }

  @Override
  public void render(TerminalLayer layer) {
    final var benchmark = benchmarkModel.getBenchmark();
    final var renderer = new BenchmarkRenderer(characterFactory, benchmark);
    renderer.render(layer);
  }
}
