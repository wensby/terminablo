package com.wensby.terminablo;

import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.scene.SceneStack;

public class SceneStackRenderer implements Renderer {

  private final SceneStack sceneStack;

  SceneStackRenderer(SceneStack sceneStack) {
    requireNonNull(sceneStack);
    this.sceneStack = sceneStack;
  }

  @Override
  public void render() {
    sceneStack.render();
  }
}
