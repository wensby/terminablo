package com.wensby.userinterface.linux;

import com.wensby.userinterface.RuntimeProcessExecutor;

import static java.util.Objects.requireNonNull;

import java.io.InputStream;
import java.io.PrintStream;

public class LinuxTerminal {

  private final InputStream inputStream;
  private final PrintStream printStream;
  private final RuntimeProcessExecutor runtimeProcessExecutor;
  private final LinuxTerminalRenderCommandFactory commandFactory;

  public LinuxTerminal(InputStream inputStream, PrintStream printStream,
      final RuntimeProcessExecutor runtimeProcessExecutor,
      LinuxTerminalRenderCommandFactory commandFactory) {
    this.inputStream = inputStream;
    this.printStream = printStream;
    this.runtimeProcessExecutor = runtimeProcessExecutor;
    this.commandFactory = requireNonNull(commandFactory);
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

  public void showCursor(boolean show) {
    if (show) {
      printStream.print(commandFactory.createShowCursorCommand().toRenderString());
    }
    else {
      printStream.print(commandFactory.createHideCursorCommand().toRenderString());
    }
  }

  public void moveCursor(int row, int column) {
    printStream.print(commandFactory.createMoveCursorCommand(row, column).toRenderString());
  }
}
