package com.wensby.terminablo.scene;

import java.util.Deque;
import java.util.LinkedList;

public class SceneStackImpl implements SceneStack {

  private final Deque<Scene> scenes;

  public SceneStackImpl() {
    scenes = new LinkedList<>();
  }

  @Override
  public boolean isEmpty() {
    return scenes.isEmpty();
  }

  @Override
  public void push(Scene scene) {
    scenes.add(scene);
  }

  @Override
  public void pop() {
    scenes.removeLast();
  }

  @Override
  public Scene getTop() {
    return scenes.getLast();
  }
}
