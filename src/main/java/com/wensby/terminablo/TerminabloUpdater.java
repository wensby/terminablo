package com.wensby.terminablo;

import com.wensby.application.ApplicationUpdater;
import com.wensby.application.UpdateResult;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.application.userinterface.UserInput;

import java.time.Duration;

public class TerminabloUpdater implements ApplicationUpdater {

  private final SceneStack sceneStack;

  public TerminabloUpdater(SceneStack sceneStack) {
    this.sceneStack = sceneStack;
  }

  @Override
  public UpdateResult updateApplication(Duration elapsedTime, UserInput userInput) {
    sceneStack.getTop().update(elapsedTime, userInput);
    if (sceneStack.isEmpty()) {
      return UpdateResult.FINAL_UPDATE;
    }
    return UpdateResult.CONTINUE;
  }
}
