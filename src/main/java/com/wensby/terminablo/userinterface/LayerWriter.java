package com.wensby.terminablo.userinterface;

import com.wensby.application.userinterface.InterfaceLocation;

public interface LayerWriter {

  void write(String text, InterfaceLocation location);

  void write(DecoratedText text, InterfaceLocation location);

}
