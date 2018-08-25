package com.wensby.terminablo;

import com.wensby.userinterface.UserInput;

import java.time.Duration;

public interface Scene {

    Scene update(Duration elapsedTime, UserInput input);
}
