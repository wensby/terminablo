package com.wensby.application;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

public class BenchmarkModelImpl implements BenchmarkModel {

  private boolean displayed;
  private Duration lastUpdateTime = Duration.ZERO;
  private Duration lastRenderTime = Duration.ZERO;
  private List<Duration> latestTickTimes = new LinkedList<>(List.of(Duration.ZERO));

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
    latestTickTimes.add(0, duration);
    if (latestTickTimes.size()>1000) {
      latestTickTimes = new LinkedList<>(latestTickTimes.subList(0, 900));
    }
  }

  @Override
  public Benchmark getBenchmark() {
    return new BenchmarkImpl(lastUpdateTime, lastRenderTime, latestTickTimes);
  }
}
