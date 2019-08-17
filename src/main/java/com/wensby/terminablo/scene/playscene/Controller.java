package com.wensby.terminablo.scene.playscene;

import com.wensby.userinterface.UserInput;

import java.time.Duration;

public interface Controller {
  void update(Duration elapsedTime, UserInput input);
}
