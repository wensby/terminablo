package com.wensby.terminablo;

import com.wensby.application.TerminalApplicationBuilder;
import com.wensby.application.TerminalApplicationRunner;
import com.wensby.userinterface.BashCommandExecutor;
import com.wensby.userinterface.linux.LinuxTerminal;
import com.wensby.userinterface.linux.LinuxTerminalRenderCommandFactory;

import static java.lang.System.in;
import static java.lang.System.out;

public class Terminablo {

  public static void main(String[] args) {
    var bashCommandExecutor = new BashCommandExecutor();
    var commandFactory = new LinuxTerminalRenderCommandFactory();
    var linuxTerminal = new LinuxTerminal(in, out, bashCommandExecutor, commandFactory);
    var applicationBuilder = new TerminalApplicationBuilder();
    var terminablo = new TerminabloFactory(linuxTerminal, commandFactory).createTerminablo(applicationBuilder);
    new TerminalApplicationRunner(linuxTerminal, terminablo).start();
  }

}
