package com.wensby.application.userinterface;

import com.wensby.terminablo.userinterface.TerminalCanvas;

import java.io.PrintStream;

public class TerminalCanvasImpl implements TerminalCanvas {

  private final TerminalFrameFactory frameFactory;
  private final PrintStream printStream;
  private final LinuxTerminalRenderCommandFactory commandFactory;
  private final LayerDifferenceCalculatorFactory differenceCalculatorFactory;

  private TerminalFrame previousFrame = null;

  public TerminalCanvasImpl(TerminalFrameFactory frameFactory, PrintStream printStream,
      final LinuxTerminalRenderCommandFactory commandFactory,
      final LayerDifferenceCalculatorFactory differenceCalculatorFactory) {
    this.frameFactory = frameFactory;
    this.printStream = printStream;
    this.commandFactory = commandFactory;
    this.differenceCalculatorFactory = differenceCalculatorFactory;
  }

  @Override
  public TerminalFrame createFrame() {
    return frameFactory.createFrame();
  }

  @Override
  public void renderFrame(TerminalFrame frame) {
    if (previousFrame == null || !previousFrame.getSize().equals(frame.getSize())) {
      printStream.print(commandFactory.createClearScreenCommand().toRenderString());
      previousFrame = null;
    }
    var differenceCalculator = differenceCalculatorFactory.createLayerDifferenceCalculator(previousFrame, frame);
    var differingCharacters = differenceCalculator.getDifference();
    var command = commandFactory.createCommand(differingCharacters);
    printStream.print(command.toRenderString());
    printStream.flush();
    previousFrame = frame;
  }

}
