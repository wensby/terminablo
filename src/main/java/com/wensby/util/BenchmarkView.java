package com.wensby.util;

import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalLayer;

public interface BenchmarkView {

  TerminalLayer render(InterfaceSize size);
}
