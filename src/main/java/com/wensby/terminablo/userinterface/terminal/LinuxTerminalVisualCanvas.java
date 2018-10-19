package com.wensby.terminablo.userinterface.terminal;

import com.wensby.terminablo.userinterface.VisualCanvas;
import com.wensby.userinterface.TerminalFrame;
import java.io.PrintStream;

public class LinuxTerminalVisualCanvas implements VisualCanvas {

  private final LinuxTerminalFrameFactory frameFactory;
  private final PrintStream printStream;
  private final LinuxTerminalRenderCommandFactory commandFactory;

  public LinuxTerminalVisualCanvas(LinuxTerminalFrameFactory frameFactory, PrintStream printStream,
      final LinuxTerminalRenderCommandFactory commandFactory) {
    this.frameFactory = frameFactory;
    this.printStream = printStream;
    this.commandFactory = commandFactory;
  }

  public TerminalFrame createFrame() {
    return frameFactory.createFrame();
  }

  public void renderFrame(TerminalFrame frame) {
    TerminalRenderCommand command = commandFactory.createCommand(frame.getCharacters());
    printStream.print(command.toRenderString());
    printStream.flush();
  }
}
