package com.wensby.application;

import com.wensby.application.userinterface.TerminalUserInterface;
import com.wensby.util.BenchmarkView;

import java.util.Objects;

public class TerminalApplicationBuilder {

  private final TerminalUserInterface userInterface;
  private final BenchmarkModel benchmarkModel;
  private final BenchmarkView benchmarkView;
  private final BenchmarkController benchmarkController;

  private ApplicationUpdater applicationUpdater;
  private ApplicationRenderer applicationRenderer;
  private int ticksPerSecond;

  TerminalApplicationBuilder(TerminalUserInterface userInterface, BenchmarkModel benchmarkModel, BenchmarkView benchmarkView, BenchmarkController benchmarkController) {
    this.userInterface = Objects.requireNonNull(userInterface);
    this.benchmarkModel = Objects.requireNonNull(benchmarkModel);
    this.benchmarkView = Objects.requireNonNull(benchmarkView);
    this.benchmarkController = Objects.requireNonNull(benchmarkController);
  }

  public TerminalApplicationBuilder withUpdater(ApplicationUpdater updater) {
    this.applicationUpdater = updater;
    return this;
  }

  public TerminalApplicationBuilder withRenderer(ApplicationRenderer renderer) {
    this.applicationRenderer = renderer;
    return this;
  }

  public TerminalApplicationBuilder withTargetTicksPerSecond(int target) {
    this.ticksPerSecond = target;
    return this;
  }

  public TerminalApplication build() {
    var canvas = userInterface.getCanvas();
    var renderer = new Renderer(canvas, benchmarkModel, benchmarkView, this.applicationRenderer);
    var updater = new Updater(applicationUpdater, benchmarkController);
    return new TerminalApplication(userInterface, updater, benchmarkModel, ticksPerSecond, renderer);
  }
}
