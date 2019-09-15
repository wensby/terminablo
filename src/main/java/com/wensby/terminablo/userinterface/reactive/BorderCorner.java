package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayer;

public interface BorderCorner {

  TerminalLayer getLayer(InterfaceSize availableSize);
}