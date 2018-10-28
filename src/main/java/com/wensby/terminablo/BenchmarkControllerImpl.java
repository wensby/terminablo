package com.wensby.terminablo;

import com.wensby.BenchmarkController;
import com.wensby.terminablo.userinterface.Key;
import com.wensby.userinterface.UserInput;
import java.time.Duration;

public class BenchmarkControllerImpl implements BenchmarkController {

  private final BenchmarkModel benchmarkModel;

  public BenchmarkControllerImpl(BenchmarkModel benchmarkModel) {
    this.benchmarkModel = benchmarkModel;
  }

  @Override
  public void update(Duration elapsedTime, UserInput userInput) {
    if (userInput.getKeyStrokes().contains(Key.PARAGRAPH)) {
      benchmarkModel.setDisplayed(!benchmarkModel.isDisplayed());
    }
  }
}
