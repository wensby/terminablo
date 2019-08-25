package com.wensby.application;

import com.wensby.userinterface.*;
import com.wensby.userinterface.linux.*;

import static java.lang.System.in;
import static java.lang.System.out;

public class TerminalApplicationContext {

  private final LinuxTerminal terminal;
  private final TerminalCharacterFactory characterFactory;
  private final TerminalLayerFactoryImpl layerFactory;
  private final BenchmarkModelImpl benchmarkModel;
  private final TerminalUserInterface userInterface;
  private final BenchmarkViewImpl benchmarkView;
  private final BenchmarkControllerImpl benchmarkController;

  public TerminalApplicationContext() {
    var commandFactory = new LinuxTerminalRenderCommandFactory();
    var bashCommandExecutor = new BashCommandExecutor();
    terminal = new LinuxTerminal(in, out, bashCommandExecutor, commandFactory);
    characterFactory = new TerminalCharacterFactoryImpl();
    layerFactory = new TerminalLayerFactoryImpl(characterFactory);
    var layerDifferenceCalculator = new LayerDifferenceCalculator(characterFactory);
    var frameFactory = new LinuxTerminalFrameFactory(terminal, layerFactory);
    var terminalCanvas = new TerminalCanvasImpl(
        frameFactory, terminal.getOutputStream(), commandFactory, layerDifferenceCalculator);
    var terminalKeyboard = new LinuxTerminalKeyboard(terminal.getInputStream());
    userInterface = new TerminalUserInterface(terminalKeyboard, terminalCanvas);
    benchmarkModel = new BenchmarkModelImpl();
    benchmarkView = new BenchmarkViewImpl(layerFactory, characterFactory, benchmarkModel);
    benchmarkController = new BenchmarkControllerImpl(this.benchmarkModel);
  }

  public TerminalApplicationBuilder getTerminalApplicationBuilder() {
    return new TerminalApplicationBuilder(
        userInterface, benchmarkModel, benchmarkView, benchmarkController);
  }

  public TerminalCharacterFactory getCharacterFactory() {
    return characterFactory;
  }

  public TerminalLayerFactory getTerminalLayerFactory() {
    return layerFactory;
  }

  public void run(TerminalApplication terminablo) {
    new TerminalApplicationRunner(terminal, terminablo).start();
  }
}
