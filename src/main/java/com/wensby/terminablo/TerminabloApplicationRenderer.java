package com.wensby.terminablo;

import com.wensby.application.ApplicationRenderer;
import com.wensby.application.userinterface.TerminalLayerPainter;
import com.wensby.terminablo.scene.SceneStack;

import java.util.Objects;

public class TerminabloApplicationRenderer implements ApplicationRenderer {

  private final SceneStack sceneStack;

  public TerminabloApplicationRenderer(SceneStack sceneStack) {
    this.sceneStack = Objects.requireNonNull(sceneStack);
  }

  @Override
  public void renderApplication(TerminalLayerPainter painter) {
    sceneStack.getTop().render(painter);
  }
}
