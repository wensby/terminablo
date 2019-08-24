package com.wensby.application;

import static com.wensby.terminablo.userinterface.Key.END_OF_FILE;

import com.wensby.userinterface.UserInput;
import java.time.Duration;
import java.util.Objects;

class Updater {

  private final ApplicationUpdater updater;
  private final BenchmarkController benchmarkController;

  Updater(ApplicationUpdater updater, BenchmarkController benchmarkController) {
    this.updater = Objects.requireNonNull(updater);
    this.benchmarkController = benchmarkController;
  }

  public UpdateResult update(Duration elapsedTime, UserInput userInput) {
    if (isFinalTick(userInput)) {
      return UpdateResult.FINAL_UPDATE;
    } else {
      benchmarkController.update(elapsedTime, userInput);
      return updater.update(elapsedTime, userInput);
    }
  }

  private boolean isFinalTick(UserInput userInput) {
    return userInput.getKeyStrokes().contains(END_OF_FILE);
  }
}
