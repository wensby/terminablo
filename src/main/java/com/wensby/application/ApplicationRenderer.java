package com.wensby.application;

import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayer;

public interface ApplicationRenderer {

  TerminalLayer renderFrame(InterfaceSize size);
}
