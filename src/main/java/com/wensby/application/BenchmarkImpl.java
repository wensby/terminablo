package com.wensby.application;

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.util.List;

public class BenchmarkImpl implements Benchmark {

  private final Duration lastUpdateTime;
  private final Duration lastRenderTime;
  private final List<Duration> latestTickTimes;

  public BenchmarkImpl(Duration lastUpdateTime, Duration lastRenderTime, List<Duration> latestTickTimes) {
    this.lastUpdateTime = requireNonNull(lastUpdateTime);
    this.lastRenderTime = requireNonNull(lastRenderTime);
    this.latestTickTimes = requireNonNull(latestTickTimes);
  }

  @Override
  public Duration getLastUpdateTime() {
    return lastUpdateTime;
  }

  @Override
  public Duration getLastRenderTime() {
    return lastRenderTime;
  }

  @Override
  public Duration getLastTickTime() {
    return latestTickTimes.get(0);
  }

  @Override
  public List<Duration> getLatestTickTimes() {
    return latestTickTimes;
  }
}
