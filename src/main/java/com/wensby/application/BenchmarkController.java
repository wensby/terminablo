package com.wensby.application;

import com.wensby.terminablo.userinterface.Key;
import com.wensby.userinterface.UserInput;
import java.time.Duration;

public class BenchmarkController {

  private final BenchmarkModel benchmarkModel;

  public BenchmarkController(BenchmarkModel benchmarkModel) {
    this.benchmarkModel = benchmarkModel;
  }

  public void update(Duration elapsedTime, UserInput userInput) {
    if (userInput.getKeyStrokes().contains(Key.PARAGRAPH)) {
      benchmarkModel.setDisplayed(!benchmarkModel.isDisplayed());
    }
  }
}
