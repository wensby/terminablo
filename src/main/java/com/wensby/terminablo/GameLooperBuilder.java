package com.wensby.terminablo;

import com.wensby.Updater;
import com.wensby.terminablo.userinterface.UserInterface;

public class GameLooperBuilder {

  private Updater updater;
  private UserInterface userInterface;
  private Renderer renderer;
  private BenchmarkModel benchmarkModel;

  public GameLooperBuilder withTickable(Updater updater) {
    this.updater = updater;
    return this;
  }

  public GameLooperBuilder withUserInterface(UserInterface userInterface) {
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

  GameLooper build() {
    return new GameLooperImpl(userInterface, updater, renderer, benchmarkModel);
  }
}
