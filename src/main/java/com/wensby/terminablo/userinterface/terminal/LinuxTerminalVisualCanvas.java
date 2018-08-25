package com.wensby.terminablo.userinterface.terminal;

import com.wensby.terminablo.userinterface.VisualCanvas;
import com.wensby.userinterface.TerminalCharacter;

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

    public void render(TerminalCharacter[][] frame) {
        TerminalRenderCommand command = linuxTerminalRenderCommandFactory.createCommand(frame);
        printStream.print(command.toRenderString());
        printStream.flush();
    }

    public TerminalCharacter[][] createFrame() {
        return frameFactory.createFrame();
    }
}
