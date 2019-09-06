package com.wensby.terminablo;

import com.wensby.application.ApplicationRenderer;
import com.wensby.application.userinterface.TerminalLayer;
import com.wensby.terminablo.scene.SceneStack;

import java.util.Objects;

public class TerminabloApplicationRenderer implements ApplicationRenderer {

  private final SceneStack sceneStack;

  public TerminabloApplicationRenderer(SceneStack sceneStack) {
    this.sceneStack = Objects.requireNonNull(sceneStack);
  }

  @Override
  public void renderApplication(TerminalLayer layer) {
    sceneStack.getTop().render(layer);
  }
}
