package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.Key;
import com.wensby.application.userinterface.TerminalLayer;

import java.util.List;

public interface Component {

  void render(TerminalLayer layer);

  void sendKeys(List<Key> keys);
}
