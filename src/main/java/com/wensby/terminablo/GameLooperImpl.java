package com.wensby.terminablo;

import static com.wensby.Updater.UpdateResult.FINAL_UPDATE;
import static java.util.Objects.requireNonNull;

import com.wensby.Updater;
import com.wensby.Updater.UpdateResult;
import com.wensby.terminablo.userinterface.UserInterface;
import com.wensby.userinterface.UserInput;
import java.time.Duration;
import java.time.Instant;
import org.apache.log4j.Logger;

public class GameLooperImpl implements GameLooper {

  private static final Logger LOGGER = Logger.getLogger(GameLooperImpl.class);

  private final UserInterface userInterface;
  private final Updater updater;
  private final Renderer renderer;
  private final BenchmarkModel benchmarkModel;

  private Instant latestTickInstant;
  private UpdateResult latestUpdateResult;
  private long ticks;

  GameLooperImpl(UserInterface userInterface, Updater updater, Renderer renderer,
      BenchmarkModel benchmarkModel) {
    this.userInterface = requireNonNull(userInterface);
    this.updater = requireNonNull(updater);
    this.renderer = requireNonNull(renderer);
    this.benchmarkModel = requireNonNull(benchmarkModel);
  }

  @Override
  public void run() {
    Logger.getLogger(getClass()).debug("Running Game Loop");
    while (isRunning()) {
      tick();
      sleep();
    }
  }

  private void tick() {
    LOGGER.debug("Tick " + ticks);
    Instant tickStart = Instant.now();
    var userInput = pollUserInput();
    var elapsedTime = pollElapsedTime();
    update(elapsedTime, userInput);
    render();
    Duration tickDuration = Duration.between(tickStart, Instant.now());
    benchmarkModel.setLastUpdateTime(tickDuration);
    LOGGER.debug("Tick " + ticks + " completed in " + tickDuration);
    ticks++;
  }

  private boolean isRunning() {
    return latestUpdateResult != FINAL_UPDATE;
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

  private void update(Duration latestElapsedTime, UserInput latestUserInput) {
    latestUpdateResult = updater.update(latestElapsedTime, latestUserInput);
  }

  private void render() {
    if (isRunning()) {
      renderer.render();
    }
  }

  private void sleep() {
    try {
      Thread.sleep(50);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
