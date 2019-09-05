package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayerPainter;

public interface InterfaceComponent {

  void render(TerminalLayerPainter painter);

  /**
   * Returns the desired size of the content, or a zero size if the interface component is a
   * container content that doesn't care about the individual sizes of its children.
   */
  InterfaceSize contentSize();
}
