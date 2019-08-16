package com.wensby;

import static java.util.Objects.requireNonNull;

import java.time.Duration;

public class BenchmarkImpl implements Benchmark {

  private final Duration lastUpdateTime;
  private final Duration lastRenderTime;
  private final Duration lastTickTime;

  public BenchmarkImpl(Duration lastUpdateTime, Duration lastRenderTime, Duration lastTickTime) {
    this.lastUpdateTime = requireNonNull(lastUpdateTime);
    this.lastRenderTime = requireNonNull(lastRenderTime);
    this.lastTickTime = requireNonNull(lastTickTime);
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
    return lastTickTime;
  }
}
