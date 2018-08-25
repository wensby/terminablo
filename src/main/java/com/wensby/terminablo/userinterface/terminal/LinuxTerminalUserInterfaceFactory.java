package com.wensby.terminablo.userinterface.terminal;

import com.wensby.terminablo.userinterface.UserInterfaceFactory;

public class LinuxTerminalUserInterfaceFactory implements UserInterfaceFactory {

    private static LinuxTerminalKeyboard terminalKeyboard;
    private final LinuxTerminal linuxTerminal;
    private LinuxTerminalRenderCommandFactory linuxTerminalRenderCommandFactory;

    public LinuxTerminalUserInterfaceFactory(LinuxTerminal linuxTerminal, final LinuxTerminalRenderCommandFactory linuxTerminalRenderCommandFactory) {
        this.linuxTerminal = linuxTerminal;
        this.linuxTerminalRenderCommandFactory = linuxTerminalRenderCommandFactory;
    }

    @Override
    public LinuxTerminalUserInterface createUserInterface() {
        linuxTerminal.setTerminalRaw();
        if (terminalKeyboard == null) {
            terminalKeyboard = createLinuxTerminalKeyboard();
        }
        LinuxTerminalFrameFactory frameFactory = new LinuxTerminalFrameFactory(linuxTerminal);
        LinuxTerminalVisualCanvas canvas = new LinuxTerminalVisualCanvas(frameFactory, System.out, linuxTerminalRenderCommandFactory);
        return new LinuxTerminalUserInterface(linuxTerminal, terminalKeyboard, canvas);
    }

    private LinuxTerminalKeyboard createLinuxTerminalKeyboard() {
        return new LinuxTerminalKeyboard(System.in);
    }
}
