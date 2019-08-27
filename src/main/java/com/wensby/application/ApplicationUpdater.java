package com.wensby.application;

import com.wensby.application.userinterface.UserInput;

import java.time.Duration;

public interface ApplicationUpdater {

  UpdateResult updateApplication(Duration elapsedTime, UserInput userInput);

}
