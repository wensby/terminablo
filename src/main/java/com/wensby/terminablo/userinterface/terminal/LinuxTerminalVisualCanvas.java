package com.wensby.terminablo.userinterface.terminal;

import com.wensby.terminablo.userinterface.VisualCanvas;
import com.wensby.userinterface.TerminalCharacter;
import com.wensby.userinterface.TerminalFrame;

import java.io.PrintStream;

public class LinuxTerminalVisualCanvas implements VisualCanvas {

    private final LinuxTerminalFrameFactory frameFactory;
    private final PrintStream printStream;
    private LinuxTerminalRenderCommandFactory linuxTerminalRenderCommandFactory;

    public LinuxTerminalVisualCanvas(LinuxTerminalFrameFactory frameFactory, PrintStream printStream, final LinuxTerminalRenderCommandFactory linuxTerminalRenderCommandFactory) {
        this.frameFactory = frameFactory;
        this.printStream = printStream;
        this.linuxTerminalRenderCommandFactory = linuxTerminalRenderCommandFactory;
    }

    public void renderFrame(TerminalFrame frame) {
        TerminalRenderCommand command = linuxTerminalRenderCommandFactory.createCommand(frame.getCharacters());
        printStream.print(command.toRenderString());
        printStream.flush();
    }

    public TerminalFrame createFrame() {
        return frameFactory.createFrame();
    }
}
