package com.wensby.terminablo;

import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalLayer;

public interface Renderable {

  TerminalLayer render(InterfaceSize size);
}