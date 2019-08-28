package com.wensby.application;

import java.time.Duration;
import java.util.List;

public interface Benchmark {

  Duration getLastUpdateTime();

  Duration getLastRenderTime();

  Duration getLastTickTime();

  List<Duration> getLatestTickTimes();
}
