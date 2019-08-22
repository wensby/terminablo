package com.wensby.terminablo.scene;

public interface SceneStack {

  boolean isEmpty();

  void push(Scene scene);

  void pop();

  Scene getTop();
}
