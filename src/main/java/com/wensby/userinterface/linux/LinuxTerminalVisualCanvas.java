package com.wensby.userinterface.linux;

import com.wensby.terminablo.userinterface.VisualCanvas;
import com.wensby.userinterface.CharacterDifferenceFactory;
import com.wensby.userinterface.TerminalFrame;

import java.io.PrintStream;

public class LinuxTerminalVisualCanvas implements VisualCanvas {

  private final LinuxTerminalFrameFactory frameFactory;
  private final PrintStream printStream;
  private final LinuxTerminalRenderCommandFactory commandFactory;
  private final CharacterDifferenceFactory characterDifferenceFactory;

  private TerminalFrame previousFrame = null;

  public LinuxTerminalVisualCanvas(LinuxTerminalFrameFactory frameFactory, PrintStream printStream,
      final LinuxTerminalRenderCommandFactory commandFactory,
      final CharacterDifferenceFactory characterDifferenceFactory) {
    this.frameFactory = frameFactory;
    this.printStream = printStream;
    this.commandFactory = commandFactory;
    this.characterDifferenceFactory = characterDifferenceFactory;
  }

  public TerminalFrame createFrame() {
    return frameFactory.createFrame();
  }

  public void renderFrame(TerminalFrame frame) {
    if (previousFrame == null || !previousFrame.getSize().equals(frame.getSize())) {
      printStream.print(commandFactory.createClearScreenCommand().toRenderString());
    }
    var differingCharacters = characterDifferenceFactory.getDifference(previousFrame, frame);
    var command = commandFactory.createCommand(differingCharacters);
    printStream.print(command.toRenderString());
    printStream.flush();
    previousFrame = frame;
  }

}
