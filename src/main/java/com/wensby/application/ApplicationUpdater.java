package com.wensby.application;

import com.wensby.userinterface.UserInput;

import java.time.Duration;

public interface ApplicationUpdater {

  UpdateResult update(Duration elapsedTime, UserInput userInput);

}
