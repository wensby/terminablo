package com.wensby;

import com.wensby.userinterface.UserInput;
import java.time.Duration;

public interface Ticker {

  TickResult tick(Duration elapsedTime, UserInput userInput);

  enum TickResult {
    CONTINUE,
    FINAL_TICK;
  }
}
