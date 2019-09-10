package com.wensby.application.userinterface;

import java.io.PrintStream;

public class TerminalCanvasImpl implements TerminalCanvas {

  private final TerminalFrameFactory frameFactory;
  private final PrintStream printStream;
  private final LinuxTerminalRenderCommandFactory commandFactory;
  private final LayerDifferenceRenderCommandFactory layerDifferenceRenderCommandFactory;

  private TerminalFrame previousFrame = null;

  public TerminalCanvasImpl(TerminalFrameFactory frameFactory, PrintStream printStream,
      final LinuxTerminalRenderCommandFactory commandFactory,
      LayerDifferenceRenderCommandFactory layerDifferenceRenderCommandFactory) {
    this.frameFactory = frameFactory;
    this.printStream = printStream;
    this.commandFactory = commandFactory;
    this.layerDifferenceRenderCommandFactory = layerDifferenceRenderCommandFactory;
  }

  @Override
  public TerminalFrame createFrame() {
    return frameFactory.createFrame();
  }

  @Override
  public CanvasRenderResult renderFrame(TerminalFrame frame) {
    if (previousFrame == null || !previousFrame.size().equals(frame.size())) {
      printStream.print(commandFactory.createClearScreenCommand().toRenderString());
      previousFrame = null;
    }
    var command = layerDifferenceRenderCommandFactory.createCommand(previousFrame, frame);
    var commandRenderString = command.toRenderString();
    printStream.print(commandRenderString);
    printStream.flush();
    previousFrame = frame;
    return new CanvasRenderResult(commandRenderString.length());
  }

}
