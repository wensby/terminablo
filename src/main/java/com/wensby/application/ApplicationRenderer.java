package com.wensby.application;

import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalLayer;

public interface ApplicationRenderer {

  TerminalLayer renderFrame(InterfaceSize size);
}
