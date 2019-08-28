package com.wensby.application;

import com.wensby.application.userinterface.UserInput;
import com.wensby.application.userinterface.Key;

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
