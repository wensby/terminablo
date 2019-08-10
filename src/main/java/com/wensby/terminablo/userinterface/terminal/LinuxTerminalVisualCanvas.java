package com.wensby.terminablo.userinterface.terminal;

import com.wensby.terminablo.userinterface.VisualCanvas;
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
    if (isScreenClearNecessary(frame)) {
      printStream.print(commandFactory.createClearScreenCommand().toRenderString());
    }
    var characters = characterDifferenceFactory.getDifferingCharacters(previousFrame, frame);
    var command = commandFactory.createCommand(characters);
    printStream.print(command.toRenderString());
    printStream.flush();
    previousFrame = frame;
  }

  private boolean isScreenClearNecessary(final TerminalFrame frame) {
    return previousFrame == null || !previousFrame.getSize().equals(frame.getSize());
  }
}
