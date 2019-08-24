package com.wensby.terminablo;

import com.wensby.terminalapp.Benchmark;
import com.wensby.terminalapp.BenchmarkImpl;
import java.time.Duration;

public class BenchmarkModelImpl implements BenchmarkModel {

  private boolean displayed;
  private Duration lastUpdateTime = Duration.ZERO;
  private Duration lastRenderTime = Duration.ZERO;
  private Duration lastTickTime = Duration.ZERO;

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
  public void setLastRenderTime(Duration duration) {
    lastRenderTime = duration;
  }

  @Override
  public void setLastTickTime(Duration duration) {
    lastTickTime = duration;
  }

  @Override
  public Benchmark getBenchmark() {
    return new BenchmarkImpl(lastUpdateTime, lastRenderTime, lastTickTime);
  }
}
