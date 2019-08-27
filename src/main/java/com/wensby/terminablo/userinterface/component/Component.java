package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.InterfaceSize;

public interface Component {

  void render(char[][] canvas);

  InterfaceLocation getLocation();

  InterfaceSize getSize();
}
