package com.wensby.application;

import com.wensby.userinterface.UserInput;
import java.time.Duration;

public interface BenchmarkController {

  void update(Duration elapsedTime, UserInput userInput);
}
