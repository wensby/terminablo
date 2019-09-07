package com.wensby.application.userinterface;

public class LayerDifferenceRenderCommandFactory {
  private final LayerDifferenceCalculatorFactory differenceCalculatorFactory;
  private final LinuxTerminalRenderCommandFactory commandFactory;

  public LayerDifferenceRenderCommandFactory(LayerDifferenceCalculatorFactory differenceCalculatorFactory, LinuxTerminalRenderCommandFactory commandFactory) {
    this.differenceCalculatorFactory = differenceCalculatorFactory;
    this.commandFactory = commandFactory;
  }

  public TerminalRenderCommand createCommand(TerminalFrame previousFrame, TerminalLayer frame) {
    var differenceCalculator = differenceCalculatorFactory.createLayerDifferenceCalculator(previousFrame, frame);
    var differingCharacters = differenceCalculator.getDifference();
    return commandFactory.createCommand(differingCharacters);
  }
}
