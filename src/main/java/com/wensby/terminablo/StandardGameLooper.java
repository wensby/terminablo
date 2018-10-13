package com.wensby.terminablo;

import static java.util.Objects.requireNonNull;

import com.wensby.Ticker;
import com.wensby.Ticker.TickResult;
import com.wensby.terminablo.userinterface.UserInterface;
import com.wensby.userinterface.UserInput;
import java.time.Duration;
import java.time.Instant;

public class StandardGameLooper implements GameLooper {

  private final UserInterface userInterface;
  private final Ticker ticker;

  private Instant latestTickInstant;
  private TickResult latestTickResult;

  StandardGameLooper(UserInterface userInterface, Ticker ticker) {
    requireNonNull(userInterface);
    requireNonNull(ticker);
    this.userInterface = userInterface;
    this.ticker = ticker;
  }

  @Override
  public void run() {
    while (isRunning()) {
      var latestUserInput = pollUserInput();
      var latestElapsedTime = pollElapsedTime();
      tick(latestElapsedTime, latestUserInput);
      sleep();
    }
  }

  private boolean isRunning() {
    return latestTickResult != TickResult.FINAL_TICK;
  }

  private UserInput pollUserInput() {
    return userInterface.pollUserInput();
  }

  private Duration pollElapsedTime() {
    var now = Instant.now();
    if (latestTickInstant == null) {
      latestTickInstant = now;
    }
    var elapsedTime = Duration.between(latestTickInstant, now);
    latestTickInstant = now;
    return elapsedTime;
  }

  private void tick(Duration latestElapsedTime, UserInput latestUserInput) {
    latestTickResult = ticker.tick(latestElapsedTime, latestUserInput);
  }

  private void sleep() {
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
