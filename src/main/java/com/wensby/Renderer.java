package com.wensby;

import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayer;

public interface Renderer {

  TerminalLayer render(InterfaceSize size);
}
