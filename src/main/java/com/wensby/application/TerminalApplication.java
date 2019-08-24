package com.wensby.application;

import com.wensby.userinterface.linux.LinuxTerminal;
import org.apache.log4j.Logger;


public class TerminalApplication {

  public static final Logger LOGGER = Logger.getLogger(TerminalApplication.class);

  private final LinuxTerminal linuxTerminal;
  private final GameLooper terminabloGameLooper;

  public TerminalApplication(LinuxTerminal linuxTerminal, GameLooper terminabloGameLooper) {
    this.linuxTerminal = linuxTerminal;
    this.terminabloGameLooper = terminabloGameLooper;
  }

  public void start() {
    try {
      setUpTerminal();
      terminabloGameLooper.run();
    } catch (Throwable e) {
      LOGGER.fatal("Terminal application crashed.", e);
    } finally {
      releaseTerminal();
    }
  }

  private void setUpTerminal() {
    linuxTerminal.setTerminalRaw();
    linuxTerminal.showCursor(false);
  }

  private void releaseTerminal() {
    linuxTerminal.moveCursor(0, 0);
    linuxTerminal.setTerminalCooked();
    linuxTerminal.showCursor(true);
  }
}
