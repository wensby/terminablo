package com.wensby.terminablo.userinterface.terminal;

import java.io.InputStream;
import java.io.PrintStream;

public class LinuxTerminal {

  private final InputStream inputStream;
  private final PrintStream printStream;
  private final RuntimeProcessExecutor runtimeProcessExecutor;

  public LinuxTerminal(InputStream inputStream, PrintStream printStream,
      final RuntimeProcessExecutor runtimeProcessExecutor) {
    this.inputStream = inputStream;
    this.printStream = printStream;
    this.runtimeProcessExecutor = runtimeProcessExecutor;
  }

  public void setTerminalRaw() {
    runtimeProcessExecutor.executeCommand("stty raw </dev/tty");
  }

  public void setTerminalCooked() {
    runtimeProcessExecutor.executeCommand("stty cooked </dev/tty");
  }

  public int getLines() {
    String size = runtimeProcessExecutor.executeCommand("stty size </dev/tty");
    return Integer.parseInt(size.split(" ")[0]);
  }

  public int getColumns() {
    String size = runtimeProcessExecutor.executeCommand("stty size </dev/tty");
    return Integer.parseInt(size.split(" ")[1]);
  }

}
