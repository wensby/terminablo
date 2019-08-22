package com.wensby.userinterface.linux;

import com.wensby.userinterface.*;

import java.time.Instant;

public class LinuxTerminalFrameFactory {

  private final LinuxTerminal linuxTerminal;
  private final TerminalLayerFactory layerFactory;
  private Instant cacheRefreshDeadline = Instant.now();
  private int linesCached = -1;
  private int columnsCached = -1;

  public LinuxTerminalFrameFactory(LinuxTerminal linuxTerminal, TerminalLayerFactory layerFactory) {
    this.linuxTerminal = linuxTerminal;
    this.layerFactory = layerFactory;
  }

  public TerminalFrame createFrame() {
    if (Instant.now().isAfter(cacheRefreshDeadline)) {
      updateCache();
    }
    return new TerminalFrameImpl(
        layerFactory.createBlankLayer(InterfaceSize.of(columnsCached, linesCached)));
  }

  private void updateCache() {
    linesCached = linuxTerminal.getLines();
    columnsCached = linuxTerminal.getColumns();
    cacheRefreshDeadline = Instant.now().plusSeconds(1);
  }
}
