package com.wensby.terminablo;

import com.wensby.Updater;
import com.wensby.userinterface.linux.TerminalUserInterface;

public class GameLooperBuilder {

  private Updater updater;
  private TerminalUserInterface userInterface;
  private Renderer renderer;
  private BenchmarkModel benchmarkModel;
  private int targetTicksPerSecond;

  public GameLooperBuilder withTickable(Updater updater) {
    this.updater = updater;
    return this;
  }

  public GameLooperBuilder withUserInterface(TerminalUserInterface userInterface) {
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
    this.targetTicksPerSecond = target;
    return this;
  }

  GameLooper build() {
    return new GameLooperImpl(userInterface, updater, renderer, benchmarkModel, targetTicksPerSecond);
  }
}
