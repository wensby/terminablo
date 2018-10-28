package com.wensby.terminablo;

import com.wensby.Benchmark;
import com.wensby.BenchmarkImpl;
import java.time.Duration;

public class BenchmarkModelImpl implements BenchmarkModel {

  private boolean displayed;
  private Duration lastUpdateTime = Duration.ZERO;

  @Override
  public boolean isDisplayed() {
    return displayed;
  }

  @Override
  public void setDisplayed(boolean b) {
    displayed = b;
  }

  @Override
  public void setLastUpdateTime(Duration duration) {
    lastUpdateTime = duration;
  }

  @Override
  public Benchmark getBenchmark() {
    return new BenchmarkImpl(lastUpdateTime);
  }
}
