package com.wensby.userinterface.linux;

import com.wensby.userinterface.TerminalCharacter;
import com.wensby.userinterface.TerminalFrame;
import com.wensby.userinterface.TerminalFrameImpl;
import com.wensby.userinterface.TerminalLayerImpl;

import java.time.Instant;

public class LinuxTerminalFrameFactory {

  private final LinuxTerminal linuxTerminal;
  private Instant cacheRefreshDeadline = Instant.now();
  private int linesCached = -1;
  private int columnsCached = -1;

  public LinuxTerminalFrameFactory(LinuxTerminal linuxTerminal) {
    this.linuxTerminal = linuxTerminal;
  }

  public TerminalFrame createFrame() {
    if (Instant.now().isAfter(cacheRefreshDeadline)) {
      updateCache();
    }
    return new TerminalFrameImpl(
        new TerminalLayerImpl(new TerminalCharacter[linesCached][columnsCached]));
  }

  private void updateCache() {
    linesCached = linuxTerminal.getLines();
    columnsCached = linuxTerminal.getColumns();
    cacheRefreshDeadline = Instant.now().plusSeconds(1);
  }
}
