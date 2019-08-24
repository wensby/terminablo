package com.wensby.application;

import com.wensby.userinterface.linux.LinuxTerminal;
import org.apache.log4j.Logger;


public class TerminalApplicationRunner {

  public static final Logger LOGGER = Logger.getLogger(TerminalApplicationRunner.class);

  private final LinuxTerminal linuxTerminal;
  private final TerminalApplication application;

  public TerminalApplicationRunner(LinuxTerminal linuxTerminal, TerminalApplication application) {
    this.linuxTerminal = linuxTerminal;
    this.application = application;
  }

  public void start() {
    try {
      setUpTerminal();
      application.run();
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
