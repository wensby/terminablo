package com.wensby.terminablo;

import com.wensby.userinterface.UserInput;
import java.time.Duration;

public interface Updatable {

  void update(Duration elapsedTime, UserInput input);
}
