package com.wensby.terminablo;

import com.wensby.Benchmark;
import java.time.Duration;

public interface BenchmarkModel {

  boolean isDisplayed();

  void setDisplayed(boolean b);

  void setLastUpdateTime(Duration duration);

  void setLastRenderTime(Duration duration);

  Benchmark getBenchmark();
}
