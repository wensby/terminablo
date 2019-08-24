package com.wensby.terminablo;

import static com.wensby.terminablo.userinterface.Key.END_OF_FILE;

import com.wensby.application.BenchmarkController;
import com.wensby.application.Updater;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.userinterface.UserInput;
import java.time.Duration;

public class UpdaterImpl implements Updater {

  private final SceneStack sceneStack;
  private final BenchmarkController benchmarkController;

  public UpdaterImpl(SceneStack sceneStack,
      BenchmarkController benchmarkController) {
    this.sceneStack = sceneStack;
    this.benchmarkController = benchmarkController;
  }

  @Override
  public UpdateResult update(Duration elapsedTime, UserInput userInput) {
    if (isFinalTick(userInput)) {
      return UpdateResult.FINAL_UPDATE;
    } else {
      sceneStack.getTop().update(elapsedTime, userInput);
      benchmarkController.update(elapsedTime, userInput);
      return UpdateResult.CONTINUE;
    }
  }

  private boolean isFinalTick(UserInput userInput) {
    return sceneStack.isEmpty() || userInput.getKeyStrokes().contains(END_OF_FILE);
  }
}
