package com.wensby.terminablo;

import com.wensby.terminalapp.Benchmark;
import java.time.Duration;

public interface BenchmarkModel {

  boolean isDisplayed();

  void setDisplayed(boolean b);

  void setLastUpdateTime(Duration duration);

  void setLastRenderTime(Duration duration);

  void setLastTickTime(Duration duration);

  Benchmark getBenchmark();
}
