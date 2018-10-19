package com.wensby;

import com.wensby.userinterface.UserInput;
import java.time.Duration;

public interface Updater {

  UpdateResult update(Duration elapsedTime, UserInput userInput);

  enum UpdateResult {
    CONTINUE,
    FINAL_UPDATE;
  }
}
