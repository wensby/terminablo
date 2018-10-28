package com.wensby;

import static java.util.Objects.requireNonNull;

import java.time.Duration;

public class BenchmarkImpl implements Benchmark {

  private final Duration lastUpdateTime;

  public BenchmarkImpl(Duration lastUpdateTime) {
    this.lastUpdateTime = requireNonNull(lastUpdateTime);
  }

  @Override
  public Duration getLastUpdateTime() {
    return lastUpdateTime;
  }
}
