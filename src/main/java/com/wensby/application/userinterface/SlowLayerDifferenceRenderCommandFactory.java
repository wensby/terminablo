package com.wensby.application.userinterface;

public class SlowLayerDifferenceRenderCommandFactory implements LayerDifferenceRenderCommandFactory {
  private final LayerDifferenceCalculatorFactory differenceCalculatorFactory;
  private final LinuxTerminalRenderCommandFactory commandFactory;

  public SlowLayerDifferenceRenderCommandFactory(LayerDifferenceCalculatorFactory differenceCalculatorFactory, LinuxTerminalRenderCommandFactory commandFactory) {
    this.differenceCalculatorFactory = differenceCalculatorFactory;
    this.commandFactory = commandFactory;
  }

  @Override
  public TerminalRenderCommand createCommand(TerminalFrame previousFrame, TerminalFrame frame) {
    var differenceCalculator = differenceCalculatorFactory.createLayerDifferenceCalculator(previousFrame, frame);
    var differingCharacters = differenceCalculator.getDifference();
    return commandFactory.createCommand(differingCharacters);
  }
}
