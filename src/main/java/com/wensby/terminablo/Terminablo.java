package com.wensby.terminablo;

import com.wensby.userinterface.BashCommandExecutor;
import com.wensby.userinterface.linux.LinuxTerminal;
import com.wensby.userinterface.linux.LinuxTerminalRenderCommandFactory;
import org.apache.log4j.Logger;

import static java.lang.System.in;
import static java.lang.System.out;

public class Terminablo {

  public static void main(String[] args) {
    var bashCommandExecutor = new BashCommandExecutor();
    var commandFactory = new LinuxTerminalRenderCommandFactory();
    var linuxTerminal = new LinuxTerminal(in, out, bashCommandExecutor, commandFactory);
    linuxTerminal.setTerminalRaw();
    linuxTerminal.showCursor(false);
    try {
      TerminabloFactory.createTerminabloGameLooper(linuxTerminal, commandFactory).run();
    } catch (Throwable e) {
      Logger.getLogger(Terminablo.class).fatal("Terminablo crashed.", e);
    } finally {
      linuxTerminal.moveCursor(0, 0);
      linuxTerminal.setTerminalCooked();
      linuxTerminal.showCursor(true);
    }
  }

}
