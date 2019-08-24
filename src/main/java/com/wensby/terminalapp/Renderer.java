package com.wensby.terminalapp;

import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalLayer;

public interface Renderer {

  TerminalLayer render(InterfaceSize size);
}
