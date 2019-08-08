package com.wensby.terminablo.scene;

import com.wensby.terminablo.Renderable;
import com.wensby.terminablo.Renderer;
import com.wensby.terminablo.Updatable;

public interface SceneStack {

  boolean isEmpty();

  void push(Scene scene);

  void pop();

  Scene getTop();
}