package com.wensby;

import static com.wensby.terminablo.userinterface.Key.END_OF_FILE;

import com.wensby.terminablo.scene.SceneStack;
import com.wensby.userinterface.UserInput;
import java.time.Duration;

public class SceneStackUpdater implements Updater {

  private final SceneStack sceneStack;

  public SceneStackUpdater(SceneStack sceneStack) {
    this.sceneStack = sceneStack;
  }

  @Override
  public UpdateResult update(Duration elapsedTime, UserInput userInput) {
    if (isFinalTick(userInput)) {
      return UpdateResult.FINAL_UPDATE;
    } else {
      sceneStack.update(elapsedTime, userInput);
      return UpdateResult.CONTINUE;
    }
  }

  private boolean isFinalTick(UserInput userInput) {
    return sceneStack.isEmpty() || userInput.getKeyStrokes().contains(END_OF_FILE);
  }
}
