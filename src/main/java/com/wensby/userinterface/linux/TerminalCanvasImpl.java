package com.wensby.userinterface.linux;

import com.wensby.terminablo.userinterface.TerminalCanvas;
import com.wensby.userinterface.LayerDifferenceCalculator;
import com.wensby.userinterface.TerminalFrame;

import java.io.PrintStream;

public class TerminalCanvasImpl implements TerminalCanvas {

  private final LinuxTerminalFrameFactory frameFactory;
  private final PrintStream printStream;
  private final LinuxTerminalRenderCommandFactory commandFactory;
  private final LayerDifferenceCalculator layerDifferenceCalculator;

  private TerminalFrame previousFrame = null;

  public TerminalCanvasImpl(LinuxTerminalFrameFactory frameFactory, PrintStream printStream,
      final LinuxTerminalRenderCommandFactory commandFactory,
      final LayerDifferenceCalculator layerDifferenceCalculator) {
    this.frameFactory = frameFactory;
    this.printStream = printStream;
    this.commandFactory = commandFactory;
    this.layerDifferenceCalculator = layerDifferenceCalculator;
  }

  @Override
  public TerminalFrame createFrame() {
    return frameFactory.createFrame();
  }

  @Override
  public void renderFrame(TerminalFrame frame) {
    if (previousFrame == null || !previousFrame.getSize().equals(frame.getSize())) {
      printStream.print(commandFactory.createClearScreenCommand().toRenderString());
    }
    var differingCharacters = layerDifferenceCalculator.getDifference(previousFrame, frame);
    var command = commandFactory.createCommand(differingCharacters);
    printStream.print(command.toRenderString());
    printStream.flush();
    previousFrame = frame;
  }

}
