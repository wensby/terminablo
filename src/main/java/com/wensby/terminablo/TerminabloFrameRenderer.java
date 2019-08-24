package com.wensby.terminablo;

import com.wensby.application.FrameRenderer;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalLayer;

import java.util.Objects;

public class TerminabloFrameRenderer implements FrameRenderer {

  private final SceneStack sceneStack;

  public TerminabloFrameRenderer(SceneStack sceneStack) {
    this.sceneStack = Objects.requireNonNull(sceneStack);
  }

  @Override
  public TerminalLayer renderFrame(InterfaceSize size) {
    return sceneStack.getTop().render(size);
  }
}
