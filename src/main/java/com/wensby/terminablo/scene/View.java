package com.wensby.terminablo.scene;

import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayer;

public interface View {

  TerminalLayer render(InterfaceSize size);
}
