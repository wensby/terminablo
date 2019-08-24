package com.wensby.application;

import com.wensby.terminablo.BenchmarkModel;
import com.wensby.terminablo.BenchmarkViewImpl;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayerFactory;
import com.wensby.userinterface.linux.TerminalUserInterface;

public class TerminalApplicationBuilder {

  private Updater updater;
  private TerminalUserInterface userInterface;
  private FrameRenderer renderer;
  private BenchmarkModel benchmarkModel;
  private int ticksPerSecond;
  private TerminalLayerFactory layerFactory;
  private TerminalCharacterFactory characterFactory;

  public TerminalApplicationBuilder withUpdater(Updater updater) {
    this.updater = updater;
    return this;
  }

  public TerminalApplicationBuilder withTerminalUserInterface(TerminalUserInterface userInterface) {
    this.userInterface = userInterface;
    return this;
  }

  public TerminalApplicationBuilder withRenderer(FrameRenderer renderer) {
    this.renderer = renderer;
    return this;
  }

  public TerminalApplicationBuilder withBenchmarkModel(BenchmarkModel model) {
    this.benchmarkModel = model;
    return this;
  }

  public TerminalApplicationBuilder withTargetTicksPerSecond(int target) {
    this.ticksPerSecond = target;
    return this;
  }

  public TerminalApplicationBuilder withLayerFactory(TerminalLayerFactory layerFactory) {
    this.layerFactory = layerFactory;
    return this;
  }

  public TerminalApplicationBuilder withCharacterFactory(TerminalCharacterFactory characterFactory) {
    this.characterFactory = characterFactory;
    return this;
  }

  public TerminalApplication build() {
    var canvas = userInterface.getCanvas();
    var benchmarkView = new BenchmarkViewImpl(layerFactory, characterFactory, benchmarkModel);
    var renderer = new Renderer(canvas, benchmarkModel, benchmarkView, this.renderer);
    return new TerminalApplication(userInterface, updater, benchmarkModel, ticksPerSecond, renderer);
  }
}
