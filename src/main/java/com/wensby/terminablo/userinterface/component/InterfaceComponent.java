package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayer;

public interface InterfaceComponent {

  void render(TerminalLayer layer);

  /**
   * Returns the desired size of the content, or a zero size if the interface component is a
   * container content that doesn't care about the individual sizes of its children.
   */
  InterfaceSize contentSize();
}
