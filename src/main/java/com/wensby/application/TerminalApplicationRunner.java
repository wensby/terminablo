package com.wensby.application;

import org.apache.log4j.Logger;


public class TerminalApplicationRunner {

  public static final Logger LOGGER = Logger.getLogger(TerminalApplicationRunner.class);

  private final LinuxTerminal linuxTerminal;

  private boolean running;

  TerminalApplicationRunner(LinuxTerminal linuxTerminal) {
    this.linuxTerminal = linuxTerminal;
  }

  void run(TerminalApplication application) {
    try {
      running = true;
      setUpTerminal();
      application.run();
    } catch (Throwable e) {
      LOGGER.fatal("Terminal application crashed.", e);
    } finally {
      releaseTerminal();
      running = false;
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

  public boolean isRunning() {
    return running;
  }
}
