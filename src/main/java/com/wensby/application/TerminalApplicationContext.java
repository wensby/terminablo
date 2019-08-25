package com.wensby.application;

import com.wensby.userinterface.BashCommandExecutor;
import com.wensby.userinterface.linux.LinuxTerminal;
import com.wensby.userinterface.linux.LinuxTerminalRenderCommandFactory;

import static java.lang.System.in;
import static java.lang.System.out;

public class TerminalApplicationContext {

  private LinuxTerminalRenderCommandFactory commandFactory;
  private BashCommandExecutor bashCommandExecutor;
  private LinuxTerminal linuxTerminal;

  public TerminalApplicationContext() {
    commandFactory = new LinuxTerminalRenderCommandFactory();
    bashCommandExecutor = new BashCommandExecutor();
    linuxTerminal = new LinuxTerminal(in, out, bashCommandExecutor, commandFactory);
  }

  public TerminalApplicationBuilder getTerminalApplicationBuilder() {
    return new TerminalApplicationBuilder(linuxTerminal, commandFactory);
  }

  public void run(TerminalApplication terminablo) {
    new TerminalApplicationRunner(linuxTerminal, terminablo).start();
  }
}
