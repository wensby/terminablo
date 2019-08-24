package com.wensby.terminablo;

import com.wensby.application.ApplicationRenderer;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalLayer;

import java.util.Objects;

public class TerminabloApplicationRenderer implements ApplicationRenderer {

  private final SceneStack sceneStack;

  public TerminabloApplicationRenderer(SceneStack sceneStack) {
    this.sceneStack = Objects.requireNonNull(sceneStack);
  }

  @Override
  public TerminalLayer renderFrame(InterfaceSize size) {
    return sceneStack.getTop().render(size);
  }
}
