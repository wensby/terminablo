package com.wensby.terminablo.scene;

import com.wensby.userinterface.UserInput;
import java.time.Duration;
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
  public void update(Duration elapsedTime, UserInput input) {
    if (!scenes.isEmpty()) {
      scenes.getLast().update(elapsedTime, input);
    }
  }

  @Override
  public void render() {
    if (!scenes.isEmpty()) {
      scenes.getLast().render();
    }
  }
}
