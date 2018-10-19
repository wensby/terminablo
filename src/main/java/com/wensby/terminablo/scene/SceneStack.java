package com.wensby.terminablo.scene;

import com.wensby.terminablo.Renderable;
import com.wensby.terminablo.Updatable;

public interface SceneStack extends Updatable, Renderable {

  boolean isEmpty();

  void push(Scene scene);

  void pop();
}
