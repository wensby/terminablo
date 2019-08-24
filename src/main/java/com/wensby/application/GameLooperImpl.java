package com.wensby.application;

import static com.wensby.application.Updater.UpdateResult.FINAL_UPDATE;
import static java.util.Objects.requireNonNull;

import com.wensby.application.Updater.UpdateResult;
import com.wensby.terminablo.BenchmarkModel;
import com.wensby.terminablo.Renderer;
import com.wensby.userinterface.UserInput;
import java.time.Duration;
import java.time.Instant;

import com.wensby.userinterface.linux.TerminalUserInterface;
import org.apache.log4j.Logger;

public class GameLooperImpl implements GameLooper {

  private static final Logger LOGGER = Logger.getLogger(GameLooperImpl.class);

  private final TerminalUserInterface userInterface;
  private final Updater updater;
  private final com.wensby.terminablo.Renderer renderer;
  private final BenchmarkModel benchmarkModel;
  private final int targetTicksPerSecond;

  private Instant latestPollMoment;
  private UpdateResult latestUpdateResult;
  private long ticks;
  private Duration latestTickDuration;

  GameLooperImpl(
      TerminalUserInterface userInterface,
      Updater updater,
      Renderer renderer,
      BenchmarkModel benchmarkModel,
      int targetTicksPerSecond) {
    this.userInterface = requireNonNull(userInterface);
    this.updater = requireNonNull(updater);
    this.renderer = requireNonNull(renderer);
    this.benchmarkModel = requireNonNull(benchmarkModel);
    this.targetTicksPerSecond = targetTicksPerSecond;
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
    var tickStart = Instant.now();
    var userInput = pollUserInput();
    var elapsedTime = pollElapsedTime();
    var updateStart = Instant.now();
    update(elapsedTime, userInput);
    var updateDuration = Duration.between(updateStart, Instant.now());
    benchmarkModel.setLastUpdateTime(updateDuration);
    var renderStart = Instant.now();
    render();
    var renderDuration = Duration.between(renderStart, Instant.now());
    benchmarkModel.setLastRenderTime(renderDuration);
    latestTickDuration = Duration.between(tickStart, Instant.now());
    benchmarkModel.setLastTickTime(latestTickDuration);
    LOGGER.debug("Tick " + ticks + " completed in " + latestTickDuration);
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
    if (latestPollMoment == null) {
      latestPollMoment = now;
    }
    var elapsedTime = Duration.between(latestPollMoment, now);
    latestPollMoment = now;
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
    var sleepTime = (1000 / targetTicksPerSecond) - latestTickDuration.toMillisPart();
    if (sleepTime > 0) {
      try {
        LOGGER.debug("Sleeping for " + sleepTime + "ms");
        Thread.sleep(sleepTime);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
