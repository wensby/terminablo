package com.wensby;

import com.wensby.userinterface.UserInput;

import java.time.Duration;

public interface Ticker {

    enum TickResult {
        CONTINUE,
        FINAL_TICK;
    }

    TickResult tick(Duration elapsedTime, UserInput userInput);
}
