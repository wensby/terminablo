package com.wensby.terminalapp;

import com.wensby.terminablo.BenchmarkModel;
import com.wensby.terminablo.Renderer;
import com.wensby.userinterface.linux.TerminalUserInterface;

public class GameLooperBuilder {

  private Updater updater;
  private TerminalUserInterface userInterface;
  private com.wensby.terminablo.Renderer renderer;
  private BenchmarkModel benchmarkModel;
  private int ticksPerSecond;

  public GameLooperBuilder withUpdater(Updater updater) {
    this.updater = updater;
    return this;
  }

  public GameLooperBuilder withTerminalUserInterface(TerminalUserInterface userInterface) {
    this.userInterface = userInterface;
    return this;
  }

  public GameLooperBuilder withRenderer(Renderer renderer) {
    this.renderer = renderer;
    return this;
  }

  public GameLooperBuilder withBenchmarkModel(BenchmarkModel model) {
    this.benchmarkModel = model;
    return this;
  }

  public GameLooperBuilder withTargetTicksPerSecond(int target) {
    this.ticksPerSecond = target;
    return this;
  }

  public GameLooper build() {
    return new GameLooperImpl(userInterface, updater, renderer, benchmarkModel, ticksPerSecond);
  }
}
