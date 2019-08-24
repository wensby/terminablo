package com.wensby.application;

import com.wensby.userinterface.LayerDifferenceCalculator;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayerFactory;
import com.wensby.userinterface.linux.*;

import java.util.Objects;

public class TerminalApplicationBuilder {

  private final LinuxTerminal linuxTerminal;
  private final LinuxTerminalRenderCommandFactory commandFactory;

  private ApplicationUpdater applicationUpdater;
  private ApplicationRenderer renderer;
  private int ticksPerSecond;
  private TerminalLayerFactory layerFactory;
  private TerminalCharacterFactory characterFactory;

  public TerminalApplicationBuilder(LinuxTerminal terminal, LinuxTerminalRenderCommandFactory commandFactory) {
    linuxTerminal = Objects.requireNonNull(terminal);
    this.commandFactory = Objects.requireNonNull(commandFactory);
  }

  public TerminalApplicationBuilder withUpdater(ApplicationUpdater updater) {
    this.applicationUpdater = updater;
    return this;
  }

  public TerminalApplicationBuilder withRenderer(ApplicationRenderer renderer) {
    this.renderer = renderer;
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
    var layerDifferenceCalculator = new LayerDifferenceCalculator(characterFactory);
    var frameFactory = new LinuxTerminalFrameFactory(linuxTerminal, layerFactory);
    var terminalCanvas = new TerminalCanvasImpl(frameFactory, linuxTerminal.getOutputStream(), commandFactory, layerDifferenceCalculator);
    var terminalKeyboard = new LinuxTerminalKeyboard(linuxTerminal.getInputStream());
    var userInterface = new TerminalUserInterface(terminalKeyboard, terminalCanvas);
    var canvas = userInterface.getCanvas();
    var benchmarkModel = new BenchmarkModelImpl();
    var benchmarkView = new BenchmarkViewImpl(layerFactory, characterFactory, benchmarkModel);
    var renderer = new Renderer(canvas, benchmarkModel, benchmarkView, this.renderer);
    var benchmarkController = new BenchmarkControllerImpl(benchmarkModel);
    var updater = new Updater(applicationUpdater, benchmarkController);
    return new TerminalApplication(userInterface, updater, benchmarkModel, ticksPerSecond, renderer);
  }
}