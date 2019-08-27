package com.wensby.util;

import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayer;

public interface BenchmarkView {

  TerminalLayer render(InterfaceSize size);
}
