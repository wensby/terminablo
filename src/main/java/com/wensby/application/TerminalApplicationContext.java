package com.wensby.application;

import com.wensby.application.userinterface.*;

import static java.lang.System.in;
import static java.lang.System.out;

public class TerminalApplicationContext {

  private static final TerminalApplicationContext SINGLETON = new TerminalApplicationContext();

  private final TerminalCharacterFactory characterFactory;
  private final BenchmarkModelImpl benchmarkModel;
  private final TerminalUserInterface userInterface;
  private final BenchmarkViewImpl benchmarkView;
  private final BenchmarkController benchmarkController;
  private final TerminalApplicationRunner terminalApplicationRunner;
  private final TerminalLayerFactory layerFactory;

  private TerminalApplicationContext() {
    var commandFactory = new LinuxTerminalRenderCommandFactory();
    var bashCommandExecutor = new BashCommandExecutor();
    var terminal = new LinuxTerminal(in, out, bashCommandExecutor, commandFactory);
    characterFactory = new TerminalCharacterFactoryImpl();
    layerFactory = new TerminalLayerFactoryImpl();
    var layerDifferenceCalculator = new LayerDifferenceCalculatorFactory(characterFactory);
    var frameFactory = new TerminalFrameFactory(terminal, layerFactory);
    var terminalCanvas = new TerminalCanvasImpl(
        frameFactory, terminal.getOutputStream(), commandFactory, layerDifferenceCalculator);
    var terminalKeyboard = new LinuxTerminalKeyboard(terminal.getInputStream());
    userInterface = new TerminalUserInterface(terminalKeyboard, terminalCanvas);
    benchmarkModel = new BenchmarkModelImpl();
    benchmarkView = new BenchmarkViewImpl(characterFactory, benchmarkModel);
    benchmarkController = new BenchmarkController(this.benchmarkModel);
    terminalApplicationRunner = new TerminalApplicationRunner(terminal);
  }

  public static TerminalApplicationContext getContext() {
    return SINGLETON;
  }

  public TerminalApplicationBuilder getApplicationBuilder() {
    return new TerminalApplicationBuilder(
        userInterface, benchmarkModel, benchmarkView, benchmarkController);
  }

  public TerminalCharacterFactory getCharacterFactory() {
    return characterFactory;
  }

  public void run(TerminalApplication application) {
    if (terminalApplicationRunner.isRunning()) {
      throw new RuntimeException("Terminal application context already running application");
    }
    terminalApplicationRunner.run(application);
  }

  public TerminalLayerFactory getLayerFactory() {
    return layerFactory;
  }
}
